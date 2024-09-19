## Note 
Note for working with file permission in linux 
```bash
ll 
useradd 
adduser

# create two users 
# /bin/sh
sudo useradd \
    --shell /bin/bash \
    --create-home spiderman

sudo useradd \
    --shell /bin/bash \
    --create-home superman

sudo passwd spiderman
su - spiderman # login and go to homedir of this user 

echo "Hello world " > message.txt 
su superman

# 777 , 000 , 644 , 755

# a = all 
# u = user
# g = group
# o = other

# + = add permission
# - = remove permission
# = = set permission (overwrite )
chmod o-r message.txt # remove read permission from other
chmod g-x,o-x test.sh
./scriptname.sh
chmod +x test.sh

# add superman user to the spiderman group 
sudo usermod -aG spiderman superman


# using numberf for setting permission 
sudo chmod 777 message.txt # 7 -> user , 7 -> group , 7 -> others

chown newuser file # or directory
sudo chown superman message.txt
sudo chown superman:superman message.txt
chown user:group file # or directory


```
* How to make regular user become a sudoer 
```bash
sudo usermod -aG sudo spiderman


# customize more priviledges
sudo visudo 
sudo -k # clear session of sudo 
```