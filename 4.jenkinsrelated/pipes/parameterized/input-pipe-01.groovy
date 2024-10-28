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

        stage("Stage Two"){
            // Stage or Production 
            // input ( boolean, string, choices)
            steps{
                script{
                    def deployOption=input(
                        message: "Choose your environment" ,
                        parameters: [
                             choice(name: 'DEPLOY_ENV',
                              choices: ['staging', 'production'], description: 'Select deployment environment')
     
                        ]
                    )

                    echo "Deploy option is : ${deployOption}"
                    // if deployOption==....
                }
            }
        }
    }

    post{
        success{
            sh " echo ' this is the successful message ' "
        }
        always{
            sh """
            echo " this is will always get executed "
            """
        }
        failure{
            sh " echo' this is failed message '"
        }
    }
}