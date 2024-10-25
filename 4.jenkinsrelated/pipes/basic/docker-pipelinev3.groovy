pipeline {
    agent any 

    environment {
        REGISTRY="kube-nexus-registry.devnerd.store"
        IMAGE_NAME="reactjs-nginx-pipe-nexus"
        TAG = "v0.0.${env.BUILD_NUMBER}"
    }

    stages {
        stage("Git Checkout") {
            steps {
                git "https://gitlab.com/lyvanna544/reactjs-devop8-template.git"
            }
        }

        stage("Build Image") {
            steps {
                script {
                    // Build the Docker image using the Dockerfile in the repository
                    def dockerImage = docker.build("${REGISTRY}/${IMAGE_NAME}:${TAG}")
                }
            }
        }

        stage("Push Image") {
            steps {
                script {
                    // Log in to Docker registry using credentials stored in Jenkins
                    docker.withRegistry('https://kube-nexus-registry.devnerd.store', 'NEXUSOSS') {
                        // Push the Docker image to the Docker registry
                        def dockerImage = docker.image("${REGISTRY}/${IMAGE_NAME}:${TAG}")
                        dockerImage.push()
                    }
                }
            }
        }

        stage("Deploy Service") {
            steps {
                script {
                    // Stop and remove the existing container, if it exists
                    sh 'docker stop reactjs-pipe-cont || true'
                    sh 'docker rm reactjs-pipe-cont || true'

                    // Run a new container using the pushed Docker image
                    docker.image("${REGISTRY}/${IMAGE_NAME}:${TAG}").run("-d -p 3000:80 --name reactjs-pipe-cont")
                }
            }
        }
    }

  
}
