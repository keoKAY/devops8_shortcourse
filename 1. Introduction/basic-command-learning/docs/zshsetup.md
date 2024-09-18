## Setup OMZ 
1. git 
2. zsh 

```bash 

# 1. Install the zsh 
sudo install zsh -y 

# confirm installation
which zsh 
zsh --version
# Check current shell 
echo $SHELL


# 2. Run this command in order to install the zsh 
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"


# 3. Clone this plugins in order easily work with the zsh plugins include 
# zsh-autosuggestions and zsh-syntax-highlighting
git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions
git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting



# 4. Update the .zshrc file
# Add the plugins in the .zshrc file
plugins=(git zsh-autosuggestions zsh-syntax-highlighting)

# 5. restart the terminal
```