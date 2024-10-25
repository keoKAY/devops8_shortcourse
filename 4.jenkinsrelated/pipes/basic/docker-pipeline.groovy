pipeline{
    agent any 
    stages{
    
        stage("Stage One"){
            steps{
                script{
                    docker.image('node:lts').inside{
                    sh "node -v "
                    sh "npm -v"
                }
                }
                
            }
        }
        
    }
}