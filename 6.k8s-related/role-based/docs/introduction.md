## NOTE FOR ROLEBASED ACCESS CONTROL 
1. THe core component 
Kubernetes RBAC uses four main objects to decided "Who can do what " 
- `Role`: Define permission within a specific namespaces (ex. "can read pods in `dev`)
- `ClusterRole`: Defines permissions cluster-side (ex. "can list nodes" or "can read pods in ALL namespaces " )
- `RoleBinding`: Grant a **ROLE** to a user/group in a specific namespaces. 
- `ClusterRoleBinding`: Grants a **ClusterRole** to a user/gropup for the whole cluster 

*** 
- Practice: 
let's simulate the real team setup. We will create a `Developer` role that can only manage apps in the `alpha-team` namespace, but cannot touch `kube-system` or delete your Longhorn storages

1. Create the namespace 
```bash 
kubectl create namespace alpha-team 
```
2. Create a Role (The "What" )
This role allows viewing everything but only modifying "workloads" (Deployment, Pods). It specifically cannnot see Secrets ( very common in the productiosn to prevent devs from seeing API keys )
```yaml 
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  namespace: alpha-team
  name: developer-role
rules:
- apiGroups: ["", "apps", "batch"]
  resources: ["pods", "deployments", "services", "jobs"]
  verbs: ["get", "list", "watch", "create", "update", "patch", "delete"]
```

3. Bind the Role to  a User ( THE "WHO " )
In production, you don't usually use the admin certificate. You use a ServiceAccount or an external Identity like (Google , Azure, LDAP ). 
Let's create a serviceaccount for the user called "BOB" 
```bash 
kubectl create serviceaccount bob -n alpha-team
```
Connect Bob to the developer role: 
```yaml 
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: bob-developer-binding
  namespace: alpha-team
subjects:
- kind: ServiceAccount
  name: bob
  namespace: alpha-team
roleRef:
  kind: Role
  name: developer-role
  apiGroup: rbac.authorization.k8s.io

```
3. THe "Production Tests" 
The best way to learn RBAC is to "Impersonate" the user to see if your security workds. Use `auth can-i` command. 

- Check if Bob can list pods in his namespace (should be YES)
```bash 
kubectl auth can-i list pods -n alpha-team \
    --as=system:serviceaccount:alpha-team:bob 
```
- Check if Bob can view Secrets ( Should be NO , based on our role above )
```bash
kubectl auth can-i get secrets -n alpha-team \
    --as=system:serviceaccount:alpha-team:bob
```

4. Production Best Practice 
- Avoid the Wildcard (*): Never give a role `verbs: ["*"]` or `resources: ["*"]` in production. Be explicit 
- Namespace Isolation: Use `RoleBinding` instead of `ClusterRoleBinding` whenever possible. Even if you use a `ClusterRole` ( like the built-in `view` role ), binding it with a `RoleBinding` restrict the user to juse one namespaces. 
- Service Account for Apps: Your application ( like python script talking to the K8s API ) , should have their own restricted ServiceAccount, just like "BOB" 
- THe "View" Role: Kubernetes comes with a built-in ClusterRole called `view`. It's great starting pint for "Read-Only" Access for junior engineers. 