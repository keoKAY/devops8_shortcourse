pipeline {
    agent any
    tools {
        nodejs 'node-latest'
    }

    stages {
        stage('Get Code') {
            steps {
                echo  "Getting code from github..."
                git 'https://github.com/sexymanalive/reactjs-devop8-template'
                sh 'ls -lrt'
            }
        }

        stage("Install dependencies "){
            steps{
                sh """
                    node -v 
                    npm -v

                    npm i 
                """
                // sh " npm install "
            }
        }
        stage("Run Tests"){
            steps {
                sh " npm test "
            }
        }

        stage("Build and Deploy"){
            steps{
                echo "Using docker compose to build and deploy "
                sh """
                    docker compose up -d
                """
            }
        }

        stage("Add domain name"){
            steps{
                echo "Run shellscript to add domain "
                sh """
                sudo bash /home/keo/utilities/adddomainssl.sh  new-reactjs  3000
                """
            }
        }
    }
}