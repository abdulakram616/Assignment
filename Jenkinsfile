pipeline {
    agent none
        stages {
            stage('Build') {
                agent {
                docker {
                filename ' ./Dockerfile'
                    }
                }
                    steps{
                        script{
                            echo 'Hello World'
                            sh """chmod +x -R ${env.WORKSPACE}"""
                            sh 'python ./helloworld.py'
                            //def path ='[]'
                            //path = readJSON file : "./location.json
                            //sh """chmod u+rx ./newDelFile.py"""
                            echo 'Building..'
                        }
                    }
            }
        }
}
