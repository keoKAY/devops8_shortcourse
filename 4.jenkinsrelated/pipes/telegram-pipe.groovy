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
                // special  character like !  . - , must  use \\ to escapeit
                // def  message = "Error deployingthewebsite\n Have  anice weekend!"
                def message ="""
                🚀 *Jenkins Build Completed*\n
                *Build Number*: ${env.BUILD_NUMBER}\n
                *Status*: ✅ SUCCESS\n
    
                """
                sendTelegramMessage(message)
            }
        }
    }
}

def sendTelegramMessage(message){
    sh """
    curl -s -X POST https://api.telegram.org/bot${TELEGRAM_TOKEN}/sendMessage \
        -d chat_id=${TELEGRAM_CHAT_ID}  \
        -d text="${message}" \
        -d parse_mode=MarkdownV2
        
            
    """
}
