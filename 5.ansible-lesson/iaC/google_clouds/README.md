## Note 
```bash
# 123
ansible-vault encrypt playbooks/vars.yml
ansible-vault decrypt playbooks/vars.yml
ansible-playbook \
    -i inventory.ini playbooks/first.yml \
    --vault-password-file secret.txt
```