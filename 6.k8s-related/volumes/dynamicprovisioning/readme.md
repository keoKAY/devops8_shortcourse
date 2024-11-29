# Setup NFS dynamic provisioning 
```bash 
helm version 
helm repo list 
helm repo add nfs-subdir-external-provisioner https://kubernetes-sigs.github.io/nfs-subdir-external-provisioner/

helm uninstall nfs-subdir-external-provisioner
# 
helm install nfs-subdir-external-provisioner nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
    --set nfs.server=10.148.0.16 \
    --set nfs.path=/srv/nfs_share
```
* In case we want to create another dynamic provisioner where it has different reclaim policy: 

1. First , we will need to create the custom value 
```yaml
# nfs-custom-values.yaml
storageClasses:
- name: nfs-client-retain
  defaultClass: false
  reclaimPolicy: Retain
  provisionerName: k8s-sigs.io/nfs-subdir-external-provisioner
  archiveOnDelete: false
  # Keep the original storageClass as well
- name: nfs-client
  defaultClass: false
  reclaimPolicy: Delete
  provisionerName: k8s-sigs.io/nfs-subdir-external-provisioner
  archiveOnDelete: false

nfs:
  server: 10.148.0.16
  path: /srv/nfs_share
```
2. Once you have the file you can run below commmand in order to create it 
```bash 
helm upgrade nfs-subdir-external-provisioner \
    nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
    -f nfs-custom-values.yaml
```
*** 
* Alternatively you can run the command instead 
```bash 
helm upgrade nfs-subdir-external-provisioner nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
    --set nfs.server=10.148.0.16 \
    --set nfs.path=/srv/nfs_share \
    --set 'storageClasses[0].name=nfs-client-retain' \
    --set 'storageClasses[0].reclaimPolicy=Retain' \
    --set 'storageClasses[0].defaultClass=false' \
    --set 'storageClasses[0].provisionerName=k8s-sigs.io/nfs-subdir-external-provisioner' \
    --set 'storageClasses[1].name=nfs-client' \
    --set 'storageClasses[1].reclaimPolicy=Delete' \
    --set 'storageClasses[1].defaultClass=false' \
    --set 'storageClasses[1].provisionerName=k8s-sigs.io/nfs-subdir-external-provisioner'
```
* in order to verify, you can run below the commands
```bash 
kubectl get storageclass 
```
* in order to use it with the PVC 
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata: 
    name: my-retained-pvc
spec:   
    storageClassName: nfs-client-retain
    accessModes: 
        - ReadWriteMany
    resources: 
        requests: 
            storage: 1Gi
```
> **Note** : creating the storage class with file seems to work , but using the custom-value.yaml approach doesn't seem to work yet ( might be dues to the invalid structure of the value.yaml file that we have customized. )