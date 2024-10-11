```bash

# edit the image part of the docker composefile 

yq e '.services.nginx-basic.image="nginx:1.2.0"' \
     docker-compose.yml

# add another key fo the nginx-basic service 
yq e '.services.nginx-basic.environment.NGINX_HOST="localhost"' \
    -i docker-compose.yml     
yq e '.services.nginx-basic.environment.NEW_VAR="localhost"' \
     docker-compose.yml     


yq e '.services.nginx-basic.environment += ["NEW_VAR=helloworld"]' docker-compose.yml
```


```bash
sed '0,/image: nginx:latest/s/image: nginx:latest/image: nginx:1.21/' \
docker-compose.yml


sed -i '/container_name: nginx-container/a\
environment:\n    NEW_VAR=value' \
docker-compose.yml


# add the environt with the indentation after the container_name...
sed '/container_name: nginx-container/a\
   environment:\n    NEW_VAR=value' \
docker-compose.yml

```
## Hint 

```bash
# .zshrc 
# 1. zsh-auto-suggestions
# 2. zsh-syntax-highlighting
plugins=(git........)

# how do we write sed command to replace the plugins part

sed 's/plugins=(git)/plugins=(git plugin1 plugin2)/' \
docker-compose.yml

# search for pattern that has plugins=( 
sed '/^plugins=(/c\plugins=(yoyoyoyoo)' \
docker-compose.yml
```


 