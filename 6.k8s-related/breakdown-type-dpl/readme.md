## NOTE for the different types of deployment
1. Deployment (Most common for microservices )
    Used for: 
    - Stateless application 
    - RestAPI 
    - Spring boot services
    - NodeJs services 
    - API Gateway 
    - Frontend apps 

Featuers  
- ROlling updates 
- Rollback Supports 
- Replicas scaling 
- Self-healing (restart failed pods ) 
Examples
```
user-service
product-service
api-gateway 
config-server
```

2. Statefulset (for stateful applications )
Used for: 
- Databases 
- Kafka 
- Redis Cluster 
- Zookeeper 
- Elastic Search 
- Any services that needs stable identity 

Features: 
- Stable pod names(db-01, db-1 , db-2) 
- Stable persistent storage 
- Orderered startup and shutdown 
Example 
- postgres-0, postgres-1, postgres-2
> Required for the clustered databases 


3. DaemonSet 
Used for: 
- Logging agent (Fluentd )
- Monitoring agents (Node exporter )
- Security agents 

Run one pod per node 

4. Job / Cronjob 
Used for: 
- batched processing 
- scheduled tasks 
- Monthly report generation 
- DB migrations 



### 2. Should we deploy in K8s or Bare Metal ?
I. Microservices with different databases
Examples: 
- user-service -> postgresql 
- product-service -> MongoDB
- analytics-service -> ClickHouse
- cache -> Redis 
> Recommendation 
> deploy microservices in kubernetes (deployments. )
### For database, we have two options 
##### 1. Option A : Database inside Kubernetes (Statefulset )
Best when: 
- you are cloud-native 
- you use managed storage (EBS, Ceph, etc )
- You use operators ( Postgres Operators , MongoDB Operators )

Pros: 
- Self-healing 
- Automated failover 
- Easier scalings 
- GitOps-friendly 
- Infrastructure as code 

Cons: 
- Requires strong storage backend 
- More complex networking 
- Requries careful backup setup 


##### 2. Option B : Database Outside Kubernetes 
Best when: 
- You need maximum performance 
- Heavy I/O workload 
- Large database cluster 
- Regulatory compliance 
- You want DB team to manage it seperately 

Pros: 
- More predictable disk performances
- easier Debugging 
- traditionn DB tooling 
- Less kubernetes complexity 

Cons: 
- Not fully cloud-native 
- Manully Scaling sometimes
- Not gitips-based 


| Components | Recommended Locations 
| -- | -- | 
| Microservices | Kubernetes | 
| Load Balancers | Kubernetes or external |
| Database Clusters | Often external or managed service |
| Kafka | Often inside the kubernetes clusters (statefulset )|



##### 3. Database Cluster (Reduce downtime & failover )
If high availability matters
Postgresql: 
-You can use : 
-   patroni 
    CrunchyData Operator
    Zalando Postgres Operator
    Pgpool-II
    HAProxy
    Keepalived

- Inside Kubernetes:
    Use StatefulSet + Operator
    Automatic leader election
    Automatic failover

- Outside Kubernetes:
    Use Patroni + etcd
    HAProxy in front
    Keepalived for virtual IP


Kubernetes doesn't replace database clsutering logic: 
Database HA must be handled by: 
- Database repication 
- Leader Elections 
- WAL Shipping
- ETCD/Consul 

Kubernete only: 
- restart pods 
- Reschedules pod 

##### Production Load Balancer in Kubernetes 
We have 3 main approach: 
If using: 
- AWS -> ELB / NLB 
- GCP -> Cloud LB 
- Azure -> Azure LB 

```yaml 
type: LoadBalancer 
```

- **Option2**: Using with METALLB ( BareMetal )
if we are running on 
- Premises 
- K3s 
- Bare metal clusters 

Use MetalLB 
It assigns real IP to services. 

- **Options 3**: External HAProxy + NGINX 
Common in serious production 
```bash
Internet
   ↓
HAProxy (VM)
   ↓
Kubernetes Ingress Controller
   ↓
Services
```
Why? 
- More control 
- DDocs filtering 
- TLS offloading 
- Rate Limiting 
- WAF integrations 

###### Architecture suggested 
```bash 
Internet
   ↓
HAProxy (2 nodes + Keepalived)
   ↓
Kubernetes Cluster (3 masters HA)
   ↓
Microservices (Deployment)
   ↓
Redis (StatefulSet)
   ↓
Kafka (StatefulSet)
   ↓
PostgreSQL HA (Outside Kubernetes OR Operator-based inside)
```
- If the database mission is critical -> deploy outside kubernetes 
- Medium-scale project -> inside Kubernetes with operators is fine. 


##### When to NOT use Kubernetes 
Avoid putting the Database inside the kubernetes if : 
- You don't have reliable presistent storage 
- You don't understand storage classes 
- You don't have monitoring 
- You don't have backup strategies 
- You don't understand the quorum. 

Because DB Loss = business Loss. 


*** 
A little bit of protocol in order to search : 

- DNS 
- DHCP 
- ARP 
- HTTPS
- TSL 
- ICMP 
- NTP 
- SNMP 
- KERBEROS 