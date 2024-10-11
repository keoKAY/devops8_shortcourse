
Inside the `/home/keo` create a shellscript file called `basic-service.sh` with the following content:
```bash
#!/bin/bash

echo "This is just a oneshot service - $(date) " >> /tmp/basic-service.log
```
Add execute permission to the file by `chmod +x basic-service.sh`
1. write the service configuation in the `/etc/systemd/system/` directory
```bash
# service name is : simple.service
[Unit]
Description=your service description
After=network.target    
[Service]
ExecStart=/home/keo/basic-service.sh 
[Install]
WantedBy=default.target
```

```bash
sudo systemctl daemon-reload
sudo systemctl start simple.service
sudo systemctl status simple.service
```

## Start website with systemD

```bash
git clone https://gitlab.com/keoKAY/reactjs17-templates
```

## Install `nvm` 
```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash

# .profile , .bashrc , .zshrc
source ~/.zshrc
nvm install --lts
nvm use --lts # nvm use version


cd reactjs17-templates 
npm install 
# or 
npm i 

# to run the project
npm start 

# install serve
npm install -g serve
# inside project directory 
npm run build 
serve -s build 
```


```bash
# create a service file 
sudo vim /etc/systemd/system/reactjs.service # method one 
sudo ln -s ~/reactjs.service /etc/systemd/system/reactjs.service # method two

# ~/reactjs.service = /home/keo/reactjs.service 
sudo ln -s source destination 
# inside homedirector 
# reactjs.service 
[Unit]
Description=ReactJS Home Page website 
After=network.target
[Service]
User=keo
WorkingDirectory=/home/keo/reactjs17-templates
ExecStart=/usr/bin/serve -s build
[Install]
WantedBy=multi-user.target


# 
sudo ln -s $(which serve) /usr/bin/serve
sudo ln -s $(which node ) /usr/bin/node

which nvm 
# add soft link to /usr/bin
# symbolic link = aka symlink 
sudo ln -s /home/keo/.nvm/versions/node/v14.15.4/bin/serve /usr/bin/serve
sudo ln -s /home/keo/.nvm/versions/node/v20.17.0/bin/node
# link service to directory /etc/systemd/system/reactjs.service
sudo ln -s reactjs.service /etc/systemd/system/reactjs.service
```