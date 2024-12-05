## Note 
> Note for working with argocd 


1. Adding domain name and https for our argocd 
```bash 
# check if argocd setup for  out cluster 
kubectl get all -A | grep "argocd"
```
* check if we have any existing clusterisuer for issuer the https 
```bash 
alias k="kubectl"
k get clusterissuer 
# letsencrypt-prod  is inside the default namespace 
k get pod -n argocd 


# after we have the ingress configuration 
k apply -f argocd-ingress.yaml
k get ingress -n argocd
```
* Login to argocd web ui 

```bash 
kubectl -n argocd get secret \
    argocd-initial-admin-secret \
    -o jsonpath="{.data.password}" | base64 -d

# pass: argocdhelloworld
```


# Create first application in argocd 
```bash 
# gh , glab -> create repo from terminal 
# 1. deployment.yaml , service.yaml , ingress.yaml 


# 1. create a git project that store helm 
/home/keo/helm_projects

helm create nginx-argocd-chart
# update the values.yaml to whatever you want. 
```


# Changing the default polling interval 
* configmap , change the interval inside the configMap 
* configure with webhook of our git 
    * github
    * gitlab 
    * bitbucket 
    * ... 

* in order to get the secret of argocd in order to use in the webhook config 
```bash 
k edit secret argocd-secret -n argocd


# Add the github wehook secret for the secure communication 
webhook.github.secret: "github5555" 
```