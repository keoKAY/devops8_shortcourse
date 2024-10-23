#!/bin/bash

# Exit on any error
set -e

echo "Starting Jenkins installation with JDK 17..."

# Update system packages
sudo apt update

# Install JDK 17
echo "Installing OpenJDK 17..."
sudo apt install -y openjdk-17-jdk

# Verify Java installation
java -version

# Add Jenkins repository key
echo "Adding Jenkins repository..."
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
    /usr/share/keyrings/jenkins-keyring.asc > /dev/null

# Add Jenkins repository
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null

# Update package list again
sudo apt update

# Install Jenkins
echo "Installing Jenkins..."
sudo apt install -y jenkins

# Start Jenkins service
echo "Starting Jenkins service..."
sudo systemctl start jenkins

# Enable Jenkins to start on boot
sudo systemctl enable jenkins

# Check Jenkins status
echo "Checking Jenkins status..."
sudo systemctl status jenkins

# Get initial admin password
echo "Your initial Jenkins admin password is:"
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

echo "Jenkins installation completed!"
echo "You can access Jenkins at: http://localhost:8080"
echo "Use the admin password shown above for initial setup"
