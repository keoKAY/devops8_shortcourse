pipeline {
    agent any
    environment{
        TELEGRAM_TOKEN="TOKEN"
        TELEGRAM_CHAT_ID="CHAT_ID"
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
    
    post{
        always{
            script{
                // def  message = "Error deployingthewebsite\n Have  anice weekend!"
                def message ="""
                Good Morningfrom Jenkins  
                Congratulation !! 
                Access the website: https://new-reactjs.devnerd.store
                """
                sendTelegramMessage(message)
            }
        }
    }
}

def sendTelegramMessage(message){
    sh """
            curl -s -X POST https://api.telegram.org/bot${TELEGRAM_TOKEN}/sendMessage -d chat_id=${TELEGRAM_CHAT_ID}  -d text="${message}"
            
            """
}
