pipeline {
    agent any
        stages {
            stage('Build') {
                agent {
                    dockerfile {
                        filename 'Dockerfile'
                    }
                }
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
