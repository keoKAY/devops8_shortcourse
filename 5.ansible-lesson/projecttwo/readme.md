# Note 
> This is the note for working with project two 


## Vault ( ansible vault)


```bash 
/home/keo/Documents/devops8_shortcourse/5.ansible-lesson/projecttwo
ansible-vault encrypt playbooks/vars.yml
ansible-vault edit playbooks/vars.yml
ansible-vault decrypt playbooks/vars.yml

# enter vault password: 123
ansible-playbook -i inventory.ini \
    playbooks/first-playbook.yml \
    --ask-vault-password

echo "123" > secret.txt
ansible-playbook -i inventory.ini \
    playbooks/first-playbook.yml \
    --vault-password-file secret.txt

#  override the vars image_name
# -e = --extra-vars
ansible-playbook -i inventory.ini \
    playbooks/first-playbook.yml \
    -e "image_name=postgres:latest" \
    --vault-password-file secret.txt
```