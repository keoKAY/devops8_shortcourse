## Note 
> Working with helm in k8s cluster 


```bash 
helm repo list 
helm list 

helm repo add name url 
helm repo list 
helm repo remove name 
helm repo update 
helm install chart-name 
```

## First UseCase 
1. install  the nfs dynamic provisioner 
2. customize values.yaml


## Working with Helm Chart 

```bash 

# 1. create a simple chart for the nginx deployment 
# our director : /home/keo/devops8_shortcourse/7.helms
# cd examples
helm create nginx-chart   
# in order check the syntax of your chart definition 
helm lint nginx-chart

# in order to preview the complete configuration of your helm  chart
helm template nginx-chart 
helm template nginx-chart --values your-value-file.yaml

# release the nginx-chart 
# deployment nginx ( deployment , service , serviceaccount ... )
helm install nginx-release nginx-chart
# if you are inside the chart directory , you can simply run this command
helm install nginx-release . 
helm uninstall nginx-release nginx-chart

# grep "release-name-nginx-chart" 
kubectl get all -A | grep "nginx-release-nginx-chart"

# after we have updated the version of the image , you can upgrade it using the command : 
helm upgrade nginx-release nginx-chart 
# upgrade with new version 
helm  upgrade nginx-release nginx-chart --version 0.1.1
helm upgrade nginx-release nginx-chart --install # install ( if exist upgrade)

helm rollback nginx-release 1 
helm history nginx-release
# we have three revision ( 1 , 2 , 3 )


# Access through the FQDNS 
# CoreDNS
curl nginx-release-nginx-chart.default.svc.prod
nslookup nginx-release-nginx-chart.default.svc.prod
# Access clusterIP of the service that we have deployed 
```