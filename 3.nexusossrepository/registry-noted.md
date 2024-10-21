## Registry ( Repository )

### Using Dockerhub 
```bash 
# install jq , for easily working with json 
sudo apt install jq -y 

docker images # dcoker image ls 
docker image inspect dockerhub/reactjs-nginx:v1.0.0
docker inspect 107c398be0f1 | jq -r ".[0].Id"

# in order to push the docker image to dockerhub 
# 1. tag username/docker-image:tag(version)
# our image name= dockerhub/reactjs-nginx:v1.0.0
docker tag dockerhub/reactjs-nginx:v1.0.0 \
    69966/devop8-reactjs-nginx:v1.0.0 
docker login -u your-username # then enter the password 
docker push 69966/devop8-reactjs-nginx:v1.0.0
docker images 
docker push image-id # test to see if it works or not 

# image name must be lowercase , below is the incorrect one.
docker tag dockerhub/reactjs-nginx:v1.0.0 \
    69966/devop8-ReactJs-nginx:v1.0.0   

# pull when you want to use this image from the dockerhub 
docker pull 69966/devop8-reactjs-nginx:v1.0.0

# credentials 
cat ~/.docker/config.json 
```


### Using gitlab registry 
```bash 

docker login registry.gitlab.com
# need TOKEN -> write:registry , read:registry
# enter your username and access token 
docker build -t registry.gitlab.com/lyvanna544/storing-docker-image .

# image = nginx:alpinestoring-docker-image
docker tag nginx:alpine registry.gitlab.com/lyvanna544/storing-docker-image/nginx-aline:v1.0.0
docker push registry.gitlab.com/lyvanna544/storing-docker-image/nginx-aline:v1.0.0

# better way to do this 
export imageUrl="registry.gitlab.com/lyvanna544/storing-docker-image/nginx-aline:v1.0.0"
docker tag nginx:alpine ${imageUrl}
docker push ${imageUrl}


docker pull registry.gitlab.com/lyvanna544/storing-docker-image/nginx-aline:v1.0.0

```

### Using github registry 
```bash 
docker login ghrc.io
docker logout ghrc.io
export GITHUB_TOKEN="your-token"
export GITHUB_TOKEN="your-token"
echo $GITHUB_TOKEN | docker login ghcr.io -u keoKAY --password-stdin

# enter your username and token (read/write/delete:package)
docker tag nginx:alpine ghrc.io/sexymanalive/docker-storing/nginx-alpine:v1.0.0
docker tag nginx:alpine ghrc.io/keokay/nginx-alpine:v1.0.0
docker push ghrc.io/sexymanalive/docker-storing/nginx-alpine:v1.0.0

```