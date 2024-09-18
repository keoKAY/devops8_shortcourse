

## Note 


```bash

sudo apt update && sudo apt upgrade -y 

pwd # show the current directory that we are in 
cd # change directory 

# generate keypair ( private and public key )
ssh-keygen

ls -a # show files directory (include dotfiles )
sudo apt install tldr -y
tldr update 
tldr cd # show the manual for the cd command


cd # go to home directory 
cd ~ # go to home directory ~ = /home/username 
cd /home/keo/Document
cd ~/Document
mkdir Document # create a directory 
touch textfile.txt 

echo "Hello world " > firstfile.txt 
# write content to files 
# nano vs vim , neovim ( lazyvim )

nano filename # -> Ctrl O , Ctrl X 
vim filename  # i for insert mode , esc for command mode , :wq! for save and quit


sudo apt install tree -y 
tree . 
tree directoryname

sudo apt install nginx -y 
curl localhost # show response page of nginx 
curl ifconfig.me # show the public ip address


more /etc/passwd 
cat /etc/passwd 
getent passwd

sudo apt install neofetch -y
neofetch # show the system information

# Show system information 
uname -a


# delete file or directory 
# sudo 
rm -rf filename # directory or file 
# rm -f , rmdir 


# copy file or directory 
cp source destination
cp message.txt message-copy.txt
mv message-copy.txt destdirectory


cp *.txt destdirectory # copy all .txt files 
mv *.txt sourcedirectory # move all .txt files
```