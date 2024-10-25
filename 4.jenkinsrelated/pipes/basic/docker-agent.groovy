pipeline{

    //  use docker with this base image get the job done  
    agent {
        docker {
            image 'node:latest'
            args '-u root'
        }
    }
    stages{
        stage("Stage One"){
            steps{

                sh """
                node -v  
                npm -v 
                whoami  
                ls -lrt
                // docker  compose version 
                // docker --version
                """

            }
        }

      
    }
}