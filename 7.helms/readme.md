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
curl nginx-release-nginx-chart.default.svc.cluster.local
nslookup nginx-release-nginx-chart.default.svc.prod
# Access clusterIP of the service that we have deployed 
kubectl get configmap coredns -n kube-system -o yaml

```

* When working with multiplke environment ( using multiple values file )

```bash 

helm install release-name chart-name 

# we are inside the chart directory 
helm upgrade nginx-release .  \
    --install \
    --values prod-values.yaml
# if we run the command outside the directoryt of the chart 
helm upgrade nginx-release nginx-chart \
    --values nginx-chart/prod-values.yaml

# if prod-values.yaml is outside the directory of the chart 
helm upgrade nginx-release nginx-chart \
    ---values prod-values.yaml 



# how to copy the existing values.yaml of the chart that we want to deploy ! 
```


* Working with private images in k8s or hellm 
1. Create yaml ( SECRET , config (encrypted base64 ))
2. using commandline 
```bash 
kubectl create secret docker-registry nexus-registry-secret-v2 \
  --docker-server="registry-new.devnerd.store" \
  --docker-username=admin \
  --docker-password=nexushelloworld \
  --docker-email=example@gmail.com

```