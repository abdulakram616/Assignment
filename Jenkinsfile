pipeline {
    agent any 
    stages {
//         stage('Initialize')
//         {
//         def dockerHome = tool 'myDocker'
//         env.PATH = "${dockerHome}/bin:${env.PATH}"
//         }
        stage('Build') { 
            agent {
                withDockerContainer: {
                    image 'python:latest' 
                }
            }
            steps{
                script{
                    echo 'Hello World'
                    sh """chmod +x -R ${env.WORKSPACE}"""
                    sh 'python ./helloworld.py'
                    echo 'Building..'
        }
    }
}
    }
}
