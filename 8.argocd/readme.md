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