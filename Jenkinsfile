pipeline {
    agent {
           dockerfile {
                  filename ' ./Dockerfile'
                    }
                }
        stages {
            stage('Build') {
                
                steps{
                    script{
                        echo 'Hello World'
                        sh 'python ./helloworld.py'
                       
                        echo 'Building..'
                    }
            }
        }
    }
}
