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



* Playbook related commands 
```bash 
ansible-playbook -i inventory.ini playbooks/second-playbook.yaml -K
# --ask-become-pass 



```
* to make sure that it wont ask for password again (not for root )
- Create a new user ( james )
- Create keypair (ssh-keygen) , create a file called authorized_keys
- Copy pub key ( ansible control node ) to remote authorized_keys




## Working with jenkins 
> Normally we can ssh with the current user ( ex. username = james  )
> Now we want to allow jenkins to ssh as well ( using the existing ssh setup that james has )


```bash 
# This is the home directory of jenkins user 
cd /var/lib/jenkins

sudo rm -rf /var/lib/jenkins/.ssh # (delete directory of jenkins )
mkdir .ssh 
# Copy private key to the jenkins' home directory 
sudo cp /home/keo/.ssh/id_rsa /var/lib/jenkins/.ssh/
# change user:group of the .ssh directory 
sudo chown -R jenkins:jenkins /var/lib/jenkins/.ssh/*

```