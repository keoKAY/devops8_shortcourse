## Note 

1. Add repo of the monitoring helm chart 
```bash 
helm repo add prometheus-community \
    https://prometheus-community.github.io/helm-charts
helm repo update
# how do we install this in the specific namespace 
helm install monitor-stack-release \
    prometheus-community/kube-prometheus-stack

# To esure that everything is running 
kubectl --namespace default \
    get pods \
    -l "release=monitor-stack-release"
```

### Configure the domain name for the prometheus and grafana 
- We only need to configure the domain name for the grafana as the visualizer for seeing things
> You can find the ingress configurations 

### Configute notification channel for the alert to fired 
- We will use the telegram as our notification channel 

```bash
# 1. Run commmand i order to get the orignal value 
helm show values prometheus-community/kube-prometheus-stack > values.yaml
# 2. After we update the value file , we can upgrade helm chart 
helm upgrade \
    monitor-stack-release \
    prometheus-community/kube-prometheus-stack \
    -f values.yaml \
    -n default
```