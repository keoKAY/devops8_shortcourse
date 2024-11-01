## Note 



* For learning purposes you can install jenkins on the contaier and mount the directories for the works or experimentations that you need ( for example docker , docker compose ...) , it allows you to easily uninstall or remove it when  you no longer needed 

```bash 
1cf9690eebf044299ba4a1e8991f0f94
docker exec -it jenkins_cont /bin/bash -c 'cat /var/jenkins_home/secrets/initialAdminPassword'
```



*** 
```bash 
# add jenkins to the docker group 
sudo usermod -aG docker jenkins
sudo   systemctl  restart  jenkins


gh auth  login # token -> repo , workflow , org:read


jenkins ALL=(ALL) NOPASSWD:ALL
```

* **Practice one** : Deploy Reactjs Project 
-> Get code ( Git(gitlab, github ) )
-> Build ( docker ) -> docker image 
-> Deploy Run in the server as a container 



***
## Workingw tih glab 

```bash 
sudo snap install glab  
glab repo create projectname 
git remote add origin https://lyvanna544:gitlabtoken@gitlab.com/lyvanna544/reactjs-devop8-template.git

# set the username  password for when you push anotehr code 
git config --global credential.helper store
```



### Working with master and slave 

```bash
ssh-keygen 

# remove from knownhost 
ssh-keygen -R 34.126.90.131
ssh keo@34.126.90.131
```