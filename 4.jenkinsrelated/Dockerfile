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

# Create docker group and add jenkins user to it
RUN groupadd -g 999 docker && \
    usermod -aG docker jenkins


# later on when we need to use ansible 
RUN apt-get update && \
    apt-get install -y openjdk-17-jre && \
    apt-get clean
RUN apt-get update && \
    apt-get install -y ansible && \
    apt-get clean
# Switch back to jenkins user
USER jenkins













