# base image 
FROM nginx:alpine  
# copy index.html to customize the welcome page 
COPY index.html /usr/share/nginx/html/index.html
# start nginx 
CMD [ "nginx", "-g", "daemon off; " ]

# build image 
# docker build  -t custom-nginx:latest . 
# docker run -d -p 8080:80 --name nginx-cont custom-nginx:latest



# login to container 
# docker exec -it nginx-cont /bin/sh