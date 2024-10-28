pipeline {
    agent any
    tools {
        nodejs 'node-latest'
    }
 parameters {
        booleanParam(name: 'DEPLOY',
         defaultValue: false,
          description: 'Set to true to run the conditional stage')
         string(name: "MESSAGE", defaultValue: "HELLO WORLD ", 
         trim: true,
          description: "Sample string parameter")
    }
    stages {
        // stage('Get Code') {
        //     steps {
        //         echo  "Getting code from github..."
        //         git 'https://github.com/sexymanalive/reactjs-devop8-template'
        //         sh 'ls -lrt'
        //     }
        // }

        stage("Show value of params "){
            steps{
                sh """
                 echo "value of message is : ${params.MESSAGE}"
                 echo "value of run stage is : ${params.RUN_STAGE}"
                """
            }
        }

        stage("Run Tests"){
            steps {

                script{
                    if (params.RUN_TEST){
                        sh """
                        echo "Install dependencies " 
                        npm install 
                        echo "Running Unit Test..."
                        npm test
                        
                        """
                    }else {
                        sh "echo 'Unit Test will NOT run  ' "
                    }
                }
            }
        }

        stage("Build and Deploy"){
            when {
                expression  {
                    return params.DEPLOY == true
                }
            }
            steps{
                echo "Using docker compose to build and deploy "
                sh """
                    docker compose up -d
                """
            }
        }

        stage("Add domain name"){
            when {
                expression {
                    return params.DEPLOY  == true
                }
            }
            steps{
                echo "Run shellscript to add domain "
                // sh """
                // sudo bash /home/keo/utilities/adddomainssl.sh  new-reactjs  3000
                // """
            }
        }
    }

    post{
        // this will clean our workspace directory even it success or fail
        always{
            cleanWs()
        }
    }
}