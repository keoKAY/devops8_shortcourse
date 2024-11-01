@Library("useful-lib@master") _
pipeline{
    agent any 
     environment{
        TELEGRAM_TOKEN="7830516253:AAHfv7jv7AQSFN63UYJSnJtIIJVQNzVFqvQ"
        TELEGRAM_CHAT_ID="683081514"
    }

    stages{
        stage("Git Checkout"){
            steps{
            git "https://github.com/sexymanalive/reactjs-devop8-template"
            }
        }

        stage("Scan with sonarqube "){
            environment{
                scannerHome= tool 'sonarqube-server'
            }
            steps{
            withSonarQubeEnv(credentialsId: 'SONARQUBE-TOKEN', installationName: 'sonarqube-server') {
                script{
                
                 def projectKey = 'reactjs-devops8-template' 
                    def projectName = 'Reactjs DevOps8 template'  // Replace with your project name
                    def projectVersion = '1.0.0'  // Replace with your project version
                            // sh "${scannerHome}/bin/sonar-scanner"
                    sh """
                    ${scannerHome}/bin/sonar-scanner \
                    -Dsonar.projectKey=${projectKey} \
                    -Dsonar.projectName="${projectName}" \
                    -Dsonar.projectVersion=${projectVersion} \
                     """   
                        
                        }
            }

            }
        }

        stage("Check Quality Gate"){
            steps{
                script{
                    timeout(time: 2, unit: 'MINUTES'){
                    def qg = waitForQualityGate()
                    if ( qg.status != 'OK'){
                        sh """
                        echo " No need to build since you QG is failed "
                        """
                        currentBuild.result='FAILURE'
                        return
                    }else {
                         currentBuild.result='SUCCESS'
                    }
                }
                }
            }
        }

        stage("Build and Deploy"){
            when{
                expression {
                    return currentBuild.result == 'SUCCESS'
                }
            }
            steps{
                sh """
                docker compose up -d --build

                """
            }
        }

        stage("Push to Registry"){
             when{
                expression {
                    return currentBuild.result == 'SUCCESS'
                }
            }
            steps{
                 
                 script{
                def imageName="69966/reactjs-jenkins-nginx:v1.0.0"
                withDockerRegistry(credentialsId: 'DOCKERHUB') {
                    sh """
                    docker push ${imageName}
                    """
                }
                 }
            }
        }


    }
    post{
        success{
            // can add the condition here as well 
            script{
               
                def message = """
                Congratulations
                You can access your website here: https://new\\-reactjs\\.devnerd\\.store
                """
                sendTelegramMessage(message,TELEGRAM_TOKEN, TELEGRAM_CHAT_ID)
            }
        }
    }
}