pipeline{
    agent any 


    stages{
        stage("Git Checkout"){
            steps{
            git "https://gitlab.com/devops-trainings3/special-trainning/sample-projects/sample-restful-jpa.git"
            }
        }
        
        stage("Build"){
            steps{
                sh './gradlew clean build -x test'
            }
        }

        stage("Scan with sonarqube "){
            environment{
                scannerHome= tool 'sonarqube-server'
            }
            
            steps{
            withSonarQubeEnv(credentialsId: 'SONARQUBE-TOKEN', installationName: 'sonarqube-server') {

                script{

                
                 def projectKey = 'spring-devops8-template' 
                    def projectName = 'Spring DevOps8 template'  // Replace with your project name
                    def projectVersion = '1.0.0'  // Replace with your project version
                            // sh "${scannerHome}/bin/sonar-scanner"
                    sh """
                    ${scannerHome}/bin/sonar-scanner \
                    -Dsonar.projectKey=${projectKey} \
                    -Dsonar.projectName="${projectName}" \
                    -Dsonar.projectVersion=${projectVersion} \
                    -Dsonar.sources=src/main/java \
                    -Dsonar.java.binaries=build/classes/java/main \
                    -Dsonar.java.libraries=build/libs/*.jar
                     """   
                        
                        }
            }

            }
        }


    }
}