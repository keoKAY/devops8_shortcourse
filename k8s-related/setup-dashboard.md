## Note 
```bash 

# Change the default service of the dashboard to the NodePort
kubectl edit service/kubernetes-dashboard -n kube-system
kubectl get service/kubernetes-dashboard -n kube-system
kubectl edit svc/kubernetes-dashboard -n kube-system
# -n = namespace

# to get all the namespace in the k8s cluster 
kubectl get namespace 


# in order to create the namespace ( group resources )
kubectl create namespace kubernetes-dashboard
# apply configuration to create service account and cluster role binding 

kubectl apply -f your-config-file.yaml 

# generate the token by running this command 
kubectl create token admin-user \
    -n kubernetes-dashboard \
    --duration=24h

# to get the better information of node
kubectl get node -o wide 
```



## To reset the kubespray 
```bash 
cd kubespray
ansible-playbook -i inventory/prod/inventory.ini reset.yml


# in case you want to reinstall it 
ansible-playbook -i inventory/prod/inventory.ini cluster.yml
```


## update the configuration of the ingress nginx 
```bash 
10.148.0.16
10.148.0.18
10.148.0.19
--watch-ingress-without-class=true
--publish-status-address=10.148.0.16,10.148.0.18,10.148.0.19
```

*** 

### Working with pods 
> Note: There are two different for working with k8s object 
> 1. **Imperative** ( simpliar run docker command ) ( kubectl run ...)
> 2. **Declarative style**: ( use the yaml file for the creating the resources )
```bash 
kubectl apply -f nginx-pod.yaml
kubectl logs pod/nginx-pod 
kubectl exec -it nginx-pod -- bash
kubectl describe pod/nginx-pod
kubectl delete -f nginx-pod.yaml
kubectl get pod/nginx-pod 
kubectl get pod/nginx-pod -o yaml 
kubectl get pod/nginx-pod -o json 
# if you want to save the configurations of the output 
kubectl get pod nginx-pod -o yaml > pod-backup.yaml  

# to get pod 
kubectl get pod # get pod from default ns 
kubectl get pod -A # get all ns 


```