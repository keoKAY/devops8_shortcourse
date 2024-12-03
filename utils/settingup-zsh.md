## Run this in order to quickly setup the omz on your machine with the two popular plugsins 
```bash 
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

```
1. syntax highlighting 
2. auto suggestions 



### Running the commands 
> Use this command in order to setup omz quickly 
```bash 
git clone https://github.com/zsh-users/zsh-autosuggestions.git \
    $ZSH_CUSTOM/plugins/zsh-autosuggestions
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git \
     $ZSH_CUSTOM/plugins/zsh-syntax-highlighting

# adding this inside the ~/.zshrc
plugins=(git zsh-autosuggestions zsh-syntax-highlighting)
```

***

```bash
nexus-new-ui.devnerd.store
registry-new.devnerd.store
# installing the certbot 
sudo apt install -y nginx certbot python3-certbot-nginx
sudo certbot --nginx -d   nexus-new-ui.devnerd.store
sudo certbot --nginx -d   registry-new.devnerd.store

```