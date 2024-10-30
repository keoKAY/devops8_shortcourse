@Library("useful-lib@master") _
pipeline{
    agent any 

    environment{
        TELEGRAM_TOKEN="TOKEN"
        TELEGRAM_CHAT_ID="CHATID"
    }
    stages{

        stage("Check  files  "){
            steps {
                script{
                    def springComposeContent=libraryResource('springboot/docker-compose.yml')
                    echo "${springComposeContent}"
                }
            }
        }
        stage("First Stage "){
            steps{
            script{
                def message= """

                This message is from jenkins
                Thank you
                """
                sendTelegramMessage(message,TELEGRAM_TOKEN,TELEGRAM_CHAT_ID)
            }
            }
        }
    }
}