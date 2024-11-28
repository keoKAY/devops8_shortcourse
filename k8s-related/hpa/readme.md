## Note 

* Make some updates to the metric server configuration on the command session 
```bash
kubectl -n kube-system edit deploy metrics-server

```


* Apache Benchmark 
> Note: we will use this to perform load testing 
ex. make 10000 request to the service ! 
```bash 
sudo apt install apache2-utils
```