pipeline {
    agent any 

    environment {
        USERNAME = "69966" // Replace with your Docker Hub username
        IMAGE_NAME = "docker-pipe-reactjs"
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
                    def dockerImage = docker.build("${USERNAME}/${IMAGE_NAME}:${TAG}")
                }
            }
        }

        stage("Push Image") {
            steps {
                script {
                    // Log in to Docker registry using credentials stored in Jenkins
                    docker.withRegistry('', 'DOCKERHUB') {
                        // Push the Docker image to the Docker registry
                        def dockerImage = docker.image("${USERNAME}/${IMAGE_NAME}:${TAG}")
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
                    docker.image("${USERNAME}/${IMAGE_NAME}:${TAG}").run("-d -p 3000:80 --name reactjs-pipe-cont")
                }
            }
        }
    }

  
}
