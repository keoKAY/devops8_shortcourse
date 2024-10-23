#!/bin/bash

# Exit on any error
set -e

echo "Starting Jenkins uninstallation..."

# Stop Jenkins service
echo "Stopping Jenkins service..."
sudo systemctl stop jenkins || true
sudo systemctl disable jenkins || true

# Remove Jenkins
echo "Removing Jenkins..."
sudo apt-get remove --purge -y jenkins
sudo apt-get autoremove -y

# Remove Jenkins repository
echo "Removing Jenkins repository..."
sudo rm -f /usr/share/keyrings/jenkins-keyring.asc
sudo rm -f /etc/apt/sources.list.d/jenkins.list

# Remove Jenkins data and configuration
echo "Removing Jenkins data and configuration..."
sudo rm -rf /var/lib/jenkins
sudo rm -rf /var/cache/jenkins
sudo rm -rf /var/log/jenkins
sudo rm -rf /etc/default/jenkins
sudo rm -rf /etc/init.d/jenkins

# Clean package cache
echo "Cleaning package cache..."
sudo apt-get clean
sudo apt-get update

# Final cleanup
echo "Performing final cleanup..."
sudo apt-get autoremove -y
sudo apt-get autoclean

echo "Uninstallation completed!"
echo "Jenkins has been completely removed from your system."

# Verify Jenkins removal
echo "Verifying removal..."
if dpkg -l | grep -q jenkins; then
    echo "Warning: Some Jenkins packages might still be present"
else
    echo "Jenkins packages successfully removed"
fi