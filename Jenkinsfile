pipeline {
    agent none 
    stages {
        stage('Initialize'){
        def dockerHome = tool 'myDocker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
    }
        stage('Build') { 
            agent {
                docker {
                    image 'python:2-alpine' 
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
