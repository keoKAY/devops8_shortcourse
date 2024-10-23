pipeline {
    agent any

    stages {
        stage('Get Code') {
            steps {
                echo  "Getting code from github..."
                git 'https://github.com/sexymanalive/reactjs-devop8-template'
                sh 'ls -lrt'
            }
        }

        stage("Build"){
            steps{
                echo "Building docker image in order to deploy it"
                sh """
                    docker build -t reactjs-nginx-jenkins:v1.0.0 .
                """
            }
        }

        stage("Deploy"){
            steps{
                echo "Deploy reactjs as a container "
                sh """
                docker run -dp 3000:80 --name react-jenkin-cont reactjs-nginx-jenkins:v1.0.0

                """
            }
        }
    }
}
