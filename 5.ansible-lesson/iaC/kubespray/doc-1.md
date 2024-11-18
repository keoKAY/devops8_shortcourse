## Note 
> This is the document for setting the HA kuberntes using the kubespray.
```bash 
# clone the project from the kubesray repo on the master node 
git clone https://github.com/kubernetes-sigs/kubespray
# navigate to the kubespray directory
cd kubespray
sudo pip install -r requirements.txt

# edit the prod/inventory.ini 
# edit the k8s-cluster.yaml 
# edit the addons.yaml 


# Run the playbook 
# inside the kubespray directory 
ansible-playbook -b -v -i inventory/prod/inventory.ini cluster.yml
```