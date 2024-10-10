## 1. Installation Guide 

```bash
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update 


# here comes the installation of docker
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin



# Check the docker
docker --version 
docker -v 
docker compose version 

# test the docker permission 
docker ps 
sudo docker ps 


# add current user into the docker group 
sudo usermod -aG docker $USER
sudo usermod -aG docker keo
sudo usermod -aG docker $(whoami)


docker run hello-world 

```


##  Running portainer

```bash
docker pull portainer/portainer-ce:alpine


docker volume create portainer_data
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:alpine

```


## Running nginx container 

```bash 
docker run  -d -p 9000:80 \
  --name nginx-container  \
  nginx:alpine
```


## Using certbot to get the ssl certificate 
```bash 
sudo apt install certbot python3-certbot-nginx -y

# generate ssl for a domain
sudo certbot --nginx -d reactjs.devnerd.shop

```