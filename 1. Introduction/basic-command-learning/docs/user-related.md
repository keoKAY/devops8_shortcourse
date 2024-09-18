## note 
commands related to workingw ith user in linux machine m

```bash
id # show id , name of group  user 
groups # show group name 

echo $SHELL
cat /etc/shells

# /usr/bin/zsh
# to change shell of user 
sudo chsh -s /bin/bash bona 
sudo chsh -s /bin/bash



# 1. Create User 
useradd 
adduser 

sudo useradd therock # useful for script writing 
sudo adduser thor  # high-level  interactive 
sudo useradd --shell /usr/bin/zsh --create-home loki
sudo passwd loki
exit 
su loki  # switch to user , but doesn't change the dir
su - loki # switch user to its home directory 
```