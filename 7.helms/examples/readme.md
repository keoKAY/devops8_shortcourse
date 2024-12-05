## Note 
> this contains various example of using the helm chart in order to install/ deploy services in the k8s cluster



## Push helm to the nexus oss 
```bash 
repourl : https://nexus-new-ui.devnerd.store/
dockerurl: https://registry-new.devnerd.store/  



# package the chart in order to reduced the size and store in repo 
helm package nginx-chart 
# nginx-chart-0.1.0.tgz
# upload chart to nexus repo 
curl -u admin:nexushelloworld \
    https://nexus-new-ui.devnerd.store/repository/helm-repo/ \
    --upload-file nginx-chart-0.1.0.tgz 

helm repo list 
# helm repo add repo-url 

helm repo add my-nginx \
    https://nexus-new-ui.devnerd.store/repository/helm-repo/ \
    --username admin \
    --password nexushelloworld
helm repo update 
helm repo list 

helm install nginx-release \
     my-nginx/nginx-chart 
# nginx-chart -> it is the name of the chart that we store nexusoss 
```