pipeline {
    agent any
        stages {
            stage('Build') {
                agent {
                    docker{
                        image 'python:3.5.1'
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
