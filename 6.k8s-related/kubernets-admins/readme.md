## NOTE 
Working with the kuberbenete admins in order to administrate all the kubernetes related concepts 

### Some prerequsites for this 
- A Compactible Linux HOSTS (ex. Ubuntu 22.04)
- At least 2GiB of RAM per machine
- At Least 2CPUS for the control-plane nodes
- Full network connectivity between all machines 

#### 1. Install the container run time 
- Kubernetes require containers runtime to run on each node inorder to setup the cluster that can work together. ex.  containerd , crio
- Load required kernel modules, Kubernete networking requied relies on the kernel's ability to see bridge traffic.  
```bash 
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter 
EOF 
```
> This command serve two main purposes: 
> - This file inside this directory will load during boot 
```bash 
sudo modprobe overlay
sudo modprobel br_netfilter
# br_netfilter: allow the kernel to correct process the network packet. 

# running this so that the modules don't need to reboot to start this
```

- Configure CIS control for networking , the next setting ensure the ITP tables correctly processes bridge traffic which is important kube-proxy and CNI plugins 

```bash 
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables =1 
net.bridge.bridge-nfs-call-ip6tables =1
net.ipv4.ip_forward =1 
EOF 
```
- to avoid having to restart our machine, we can reload it by `sudo sysctl --system`
```bash 
sudo apt-get install -y containerd 
sudo mkdir -p /etc/containerd
sudo containerd config default | sudo tee /etc/containerd/config.toml


sudo sed -i 's/SystemdCgroup=false/SystemdCgroup = true/' /etc/containerd/config.toml

sudo systemctl restart containerd 

```
- the kubelete and container-runtime , must used to same cgroup driver in order to work properly ( properly managed the resources limits )


#### 2. Install the kubelet,kubectl
```bash 
sudo swapoff -a 
# to make the swapoff persistent

sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab 
sudo apt-get install -y  apt-transport-https ca-certificates curl gpg 

sudo mkdir -p -m 755 /etc/apt/keyrings 
# curl to download keyrings 
# echo "deb [signed-by=/etc/apt/keyrings...] for telling where to get the kubernetes 

# kubectl 
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"

# kubeadm 
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubeadm"


# kubelet 
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubelet"


```bash
sudo apt-mark hold kubelet kubeadm kubectl 
```
- Used in order to mark the upgrade in order to prevent updgrade. 
- Create a single cluster node 
```bash 
sudo kubeadm init --pod-network-cidr=10.244.0.0/16
mkdir -p $HOME/.kube 
sudo cp -r /etc/kubernetes/admin.conf $HOME/.kube/config 
sudo chown $(id -u):$(id -g) $HOME/.kube/config 

# removing the taints 
kubectl taint nodes --all node-role.kubernetes.io/control-plane-
```