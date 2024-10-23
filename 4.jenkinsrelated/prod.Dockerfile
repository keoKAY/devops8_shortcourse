# Dockerfile
FROM jenkins/jenkins:lts

USER root

# Install necessary packages
RUN apt-get update && \
    apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release \
    software-properties-common

# Add Docker's official GPG key
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

# Set up Docker repository
RUN echo \
    "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian \
    $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Docker CLI
RUN apt-get update && \
    apt-get install -y docker-ce-cli

# Add jenkins user to existing docker group or create if it doesn't exist
RUN groupadd -f docker && usermod -aG docker jenkins

# Set proper permissions for Docker socket
RUN touch /var/run/docker.sock && \
    chown root:docker /var/run/docker.sock && \
    chmod 660 /var/run/docker.sock

# Switch back to jenkins user
USER jenkins