### Note 
```bash 
docker ps # nexus-cont
docker exec -it  nexus /bin/bash
cat /nexus-data/admin.password 


nexus-ui.devnerd.store # used for the ui access
nexus-registry.devnerd.store # used for docker registry 

8080 # -> ui 
5000 # -> registry 

# running the shellscript in order to add the domain name and also the ssl/tls
sudo bash utilities/adddomainssl.sh nexus-ui 8080
```

#### Working with nexus oss 
* after we have our own repository , now we are trying to push the docker image to the repository. 
```bash 
docker login registry.gitlab.com

docker login -u admin -p your-password  nexus-registry.devnerd.store 
docker login -u james -p 123456789 nexus-registry.devnerd.store 
cat ~/.docker/config.json
docker logout ghcr.io 
docker logout nexus-registry.devnerd.store
docker login nexus-registry.devnerd.store
docker images 
docker tag nginx:alpine \
    nexus-registry.devnerd.store/nginx-alpine:v1.0.0
docker push nexus-registry.devnerd.store/nginx-alpine:v1.0.0



```
* if nexus not able to accept files, we have to configure the nginx confdiguration inside the registry.conf 
* `/etc/nginx/conf.d ( nexus-registry)` 
    * inside the `location /` : add `client_max_body_size 2G` 