pipeline{
    agent any 


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
                    -Dsonar.projectName=${projectName} \
                    -Dsonar.projectVersion=${projectVersion} \
                     """   
                        
                        }
            }

            }
        }


    }
}