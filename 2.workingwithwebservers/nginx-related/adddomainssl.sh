#!/bin/bash

DOMAIN_NAME=$1
SERVICE_PORT=$2


# check root permission 
if [ "$EUID" -ne 0 ]; then 
    echo "ERROR : Please run as root"
    exit 
fi

# check if the paramaters provided 
if [ -z "${DOMAIN_NAME}" ] || [ -z "${SERVICE_PORT}" ]; then 
    echo "Please provide the domain name and service port"
    echo "Usage: sudo bash $0 <domain_name> <service_port>"
    exit 
fi




CONFIG_DIR="/etc/nginx/conf.d"


# write sample configuration  
cat <<EOF | tee "${CONFIG_DIR}/${DOMAIN_NAME}.conf" > /dev/null
server {
    listen 80;
    listen [::]:80;
    listen 443 ssl;
     server_name ${DOMAIN_NAME};
    location / { 
        proxy_pass http://localhost:${SERVICE_PORT};
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto \$scheme;
    }
}
EOF

echo "Testing the configuration of nginx " 
sudo nginx -t 
echo "Restart the nginx server " 
sudo nginx -s reload 



# add SSL 
echo "Adding https for our website " 
sudo certbot --nginx -d "${DOMAIN_NAME}"
echo "Congratulation!! " 
