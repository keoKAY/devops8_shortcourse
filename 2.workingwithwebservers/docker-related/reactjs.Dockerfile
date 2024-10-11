FROM node:alpine
WORKDIR /app 
COPY package*.json ./
RUN npm install 
COPY . . 
RUN npm run build 
EXPOSE 3000
# CMD [ "serve", "-s", "build" ]
# ENTRYPOINT [ "serve", "-s", "build" ]

ENTRYPOINT [ "serve" ]
CMD [ "-s", "build" ]


# use --entrypoint to override th dockerfile 
# docker run .. --entrypoint "sh"