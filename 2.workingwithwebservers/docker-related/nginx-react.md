`prod.Dockerfile` 
`nginx.conf` 

```bash 

# build image 
docker build -t react-nginx-prod:v1.0.0 \
    -f prod.Dockerfile  . 

# run container 
docker run -dp 3000:80 \
    --name react-nginx-prod \
    react-nginx-prod:v1.0.0


```
## addding domain name and http for the reactjs website 
* domain is `nginx-reactjs.devnerd.store` , port `3000`