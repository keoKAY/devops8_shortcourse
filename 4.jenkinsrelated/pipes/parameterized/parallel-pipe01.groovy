pipeline{
    agent any 
    stages{
     stage("Stage One")
    {
        steps{
            
            sh """
            echo "this is the first stage"  
            echo "Other stages will start after this"  
            """
        }

    }
    stage("Stage Two"){
        parallel{
            stage("Push docker image to dockerhub"){
                steps{
                    sh "echo 'Pushing docker image to docker hub '" 
                }
            }
            stage("push docker image to gitlab "){
                steps {
                    echo "push docker image to gitlabb registry"
                }
            }
            stage("pushing docker  image ghcr"){
                steps{
                    echo "push docker image to ghcr registry"
                }
            }
        }
    }

    }
}