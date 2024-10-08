## note 
note for working with web server related stuff 



## Working with SSH 

```bash
ssh -i ~/.ssh/your-key.pem user@ip 



# Copy from local to remote 
scp localfile user@ip:/path/to/remote
scp  ~/Documents/devops8_shortcourse/2.workingwithwebservers/README.md \
     keo@35.247.189.149:/home/keo/localresources

# copy from remote to local
scp keo@35.247.189.149:/home/keo/reload-service.sh  . # . = current directory


currentdirectory="$(pwd)"
```

# Domain name and https 
##  How to add domain name for the service in the nginx server 
* all the configuration realted to nginx are in `/etc/nginx/` 
* `conf.d`
* `sites-available`
* `sites-enabled`


### Method One 
1. write the  nginx configuration for reverse proxying 
- need to have a domain name : `reactjs.devnerd.shop`  
- service ( reactjs service ) is running on port `3000`

1. write this config inside `sites-available` 
```bash
# name this file as reactjs.devnerd.shop
vim reactjs.devnerd.shop

```

```nginx
# two port 80,443 

server {
    listen 80;
    server_name reactjs.devnerd.store;
    location / { 
        proxy_pass http://localhost:3000;
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```
2. link the file to sites-enabled
```bash

sudo ln -s /etc/nginx/sites-available/reactjs.devnerd.shop \
    /etc/nginx/sites-enabled/reactjs.devnerd.shop
```

3. Test the configuration by running below command 
```bash
sudo nginx -t

```
4. If okay , we need to restart nginx server 
```bash
sudo systemctl restart nginx
nginx -s reload 
```


### Method Two 
1. write nginx configuration inside the directry `/etc/nginx/conf.d/website.conf` : 
```bash 
# Test syntax of the config 
sudo nginx -t 
# reload nginx 
sudo nginx -s reload 

```
## Additional 

- securiry practice ( honeypot )