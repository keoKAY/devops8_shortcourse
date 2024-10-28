pipeline{
    agent any 
    stages{
        stage("Get Code "){
            steps{
                sh """
                  echo "Clone the code from the Git"
                """
            }
        }

        stage("Trigger other job  "){
            steps{
                // make sure that you have this job inside your jenkin server 
                build 'reactjs-pipeline'
            }
        }
    }
}