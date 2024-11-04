## Note 
> Various note on using and working ansible 

* Install ansible using the apt
```bash
sudo apt update
sudo apt install software-properties-common
sudo add-apt-repository --yes --update ppa:ansible/ansible
sudo apt install ansible

# Check to see the version of ansible
ansible --version

# localhost
ssh keo@34.87.50.29 # control plane
ssh keo@34.126.90.131 # worker


ssh-keygen -R  34.126.90.131
ssh james@34.126.90.131
```

* Commands 
```bash 
 ssh-copy-id sa@server-ip

ansible -i inventory.ini all -m ping
ansible -i inventory.ini workers -m ping
ansible -i inventory.ini worker01 -m ping
```