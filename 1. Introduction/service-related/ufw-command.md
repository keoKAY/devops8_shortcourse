```bash

sudo ufw status 
# the show the number of rules 
sudo ufw status numbered
sudo ufw delete <number>  
sudo ufw enable
sudo ufw disable

sudo ufw allow ssh 
sudo ufw allow http https

# port 3000 
sudo ufw allow 3000
sudo ufw deny 3000 


nslookup domain-name # response IP address 
free -h 
htop 
du -sh path 
df -h 
```