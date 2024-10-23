## Note 



* For learning purposes you can install jenkins on the contaier and mount the directories for the works or experimentations that you need ( for example docker , docker compose ...) , it allows you to easily uninstall or remove it when  you no longer needed 

```bash 
1cf9690eebf044299ba4a1e8991f0f94
docker exec -it jenkins_cont /bin/bash -c 'cat /var/jenkins_home/secrets/initialAdminPassword'
```