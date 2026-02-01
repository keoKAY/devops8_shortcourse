## NOTE 
It is dedicated to using other storages beside the default one and the NFS


### 1. HostPath
- Downside ⚠️ 
    - Pod is tied to one node 
    - If the node dies -> data is gone 
    - No replication 
    - Bad for autoscaling 
    - Not HA-friendly 

- Good For 👍
    - Local dev 
    - Learning kubernetes 
    - Single-node clusters(Minikube, K3s Single nodes )
```yaml 
apiVersion: v1
kind: PersistentVolume
metadata:
  name: hostpath-pv
spec:
  capacity:
    storage: 10Gi

# to ensure the ACID properly remain 
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Retain
  storageClassName: hostpath-sc
  hostPath:
    path: /data/postgres
```
```yaml 
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: hostpath-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 10Gi
  storageClassName: hostpath-sc
```

### Why people avoid NFS ( and you might too )
- Single point of failures (unless clusered )
- Perforamnce issue under loads 
- File Locking Problems 
- Manual Ops & Tuning 
- Not cloud-native 

### Better alternative to NFS 
1. Local Persistent Volumes ( Best non-NFS option)
> Much better than hostPath

- Uses real disks 
- Kubernetes is aware of node affinity 
- Safer scheduling 
- Works well for databases 

```yaml 
local: 
    path: /mnt/disk/ssd1
nodeAffinity: 
    required: 
        nodeSelectorTerms: 
            - matchExpressions: 
                - key: kubernetes.io/hostname
                  operator: In 
                  values: 
                    - worker-1 
            
```
- safer than hostPath 
- Production-accetable (with care)
❌ Still node-bound 

2. CSI-based distributed sotrage (production-grade )
If you are serious about HA 

- ## Lornhorn (super pop)
    - Easy to install 
    - Replication 
    - Scapshots & backups 
    - Perfect for K3s / homelabs 

```bash 
kubectl apply -f https://raw.githubusercontent.com/longhorn/longhorn/master/deploy/longhorn.yaml

```
- HA Storage 
- No External NFS 
- Greate UI 

- ## Rook + Ceph (enterprise-grade)
    - True distributed storages 
    - Used in production cluster 
    - Very powerful 
        - complex 
        - resource-hungry 