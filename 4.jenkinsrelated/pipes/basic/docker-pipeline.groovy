pipeline{
    agent any 

    environment{
       USERNAME="69966"
       IMAGE_NAME="docker-pipe-reactjs"
       TAG="v1.0.0"
    }
    stages{
       stage("Git Checkout"){
         steps {
            git "https://gitlab.com/lyvanna544/reactjs-devop8-template.git"
         }
       }

       stage("Build Image"){
        steps{
            // Dockerfile , docker-compose.yaml
            script{
                // docker.image("node:lts").inside{
                //   sh """
                //    npm install  
                //    npm run build 
                //   """
                // }
                def dockerImage =  docker.build("${USERNAME}/${IMAGE_NAME}:${TAG}")  
                
            }
        }
       }

       stage("Push Image"){
        steps{

            script{
                  // This step should not normally be used in your script. Consult the inline help for details.
                withDockerRegistry(credentialsId: 'DOCKERHUB' ) {
                    // some block
                    sh """
                     docker push ${USERNAME}/${IMAGE_NAME}:${TAG}
                    """
                }
            }
          
        }
       }

       stage("Deploy Service"){
        steps{
            sh """
            docker stop reactjs-pipe-cont || true 
            docker rm reactjs-pipe-cont  || true 
            docker run -dp 3000:80 --name reactjs-pipe-cont ${USERNAME}/${IMAGE_NAME}:${TAG}

            """
        }
       }


       }    
    
}
        // stage("Stage One"){
        //     steps{
        //         script{
        //             docker.image('node:lts').inside{
        //             sh "node -v "
        //             sh "npm -v"
        //         }
        //         }
                
        //     }
        // }