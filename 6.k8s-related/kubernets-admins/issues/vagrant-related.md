```bash
sudo apt update
sudo apt install -y \
  qemu-kvm \
  libvirt-daemon-system \
  libvirt-clients \
  virt-manager


sudo systemctl enable libvirtd
sudo systemctl start libvirtd


sudo usermod -aG libvirt,kvm $USER 
vagrant plugin list 
# if not installed, you can install by running the command 
vagrant plugin install vagrant-libvirt



# another way  to install it  
sudo apt install -y qemu-kvm libvirt-daemon-system libvirt-clients
sudo systemctl enable --now libvirtd
sudo usermod -aG libvirt,kvm $USER

```