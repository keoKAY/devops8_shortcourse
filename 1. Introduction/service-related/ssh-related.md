```bash
sudo systemctl status sshd 



# remote to your instance 
ssh user@ip-address # default port is 22
# if ssh run port differnet port 
ssh -p 3333 user@ip-address

# ssh used PKI ( public key infrastructure )
# private key -> locate inside our home directory 
# if you have stored the private key in diff part 
ssh -i path/to/private-key -p 3333 user@ip-address

```

* Say we have different users , we want to ssh to that user , what do we do ? 
  * 1. add publickey to the file located at `~/.ssh/authorized_key`
  * 2. enable password authentication 
 location update configuration `/etc/ssh/ssh_config` or   `/etc/ssh/sshd_config`