# base image 
FROM nginx:alpine

# Run command to create directory 
# RUN apt install git -y
# RUN apk add git tree tldr 
# RUN npm install -g serve
# RUN mkdir -p testingfolder
ADD https://i.pinimg.com/736x/9d/c8/67/9dc867124538169bf2c4b57cb547c0c3.jpg . 
WORKDIR /usr/share/nginx/html
# copy index.html to customize the welcome page 
# COPY index.html index.html
ADD index.html index.html
COPY notfound.html notfound.html
# start nginx 
CMD [ "nginx", "-g", "daemon off; " ]

# build image 
# docker build  -t custom-nginx:latest . 
# docker run -d -p 8080:80 --name nginx-cont custom-nginx:latest



# login to container 
# docker exec -it nginx-cont /bin/sh