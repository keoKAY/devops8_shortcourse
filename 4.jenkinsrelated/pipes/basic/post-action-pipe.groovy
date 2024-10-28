pipeline{
    agent any 
    stages{
        stage("Stage one "){
            steps{
                sh """

                docker -v
                docker compose version 

                """
            }
        }
    }

    post{
        always{
            sh """
            echo " this is will always get executed "
            """
        }

        success{
            sh " echo 'This is the success message ' "
        }
        failure{
            // send message ( notification channel )
            sh  "  echo 'This pipeline results in failure' "
        }
        cleanup{
            cleanWs()
        }
    }
}