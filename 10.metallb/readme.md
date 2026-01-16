
# Using the service type of loadbalacner 
when we are not working with cloud kubernetes 
you can use it own by 

## Using metallb 
![alt text](image.png)

Example confnig of Metal LB 
```yaml 
apiVersion: metallb.io/v1beta1
kind: IPAddressPool 
metadata: 
    name: local-pool 
spec: 
    addresses: 
    - 192.169.1.240-192.168.1.250
    
```

## Using ingress controller 
![alt text](image-1.png)

Why is this perferered ? 
- One entry point 
- TLS termination 
- Path & Domain routing 
- Lower IP usage 


### Best Architecture for On-Prem ( What props use )
![alt text](image-2.png)



*** 
## More on the ingress appraoch 
Nginx ingress use round-robin by default 

Nginx also support multiple algorithms via annotation 

- round_robin 
- least_conn: Pods have unevent workload 
- ewma : Pods have different performance 
- hash : Session affinity 
- random two least : high-traffic system 

* Least Conection n`least_con`
```yaml
metadata: 
    annotations: 
        nginx.ingress.kubernetes.io/load-balancer: "least_cont" 
```

* Using EWMA ( Exponentially Weighted Moving Average )

Nginx Tracks: 
- Response Time 
- Failture rates 

Then perfers fast pods auomatically 
```yaml 
metadata: 
    annotation: 
    nginx.ingress.kubernetes.io/load-balance: "ewma"
```

* Session-aware routing (Sticky Sessions )
Useful when : 
- WebSockets 
- Legacy apps 
- Stateful frontend sessions 

- Cookies based affinitiy 
```yaml 
metadata:   
    annotation: 
        nginx.ingress.kubernets.io/affinity : "cookie" 
        nginx.ingress.kubernetes.io/session-cookie-name: "INGRESSCOOKIES" 
```

* wEIGHTED LOAD BALANCER 
```yaml 
nginx.ingress.kubernetes.io/upstream-hash-by: "$request_uri"

````