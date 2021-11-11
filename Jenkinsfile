pipeline {
    agent any
        stages {
            stage('Build') {
                agent {
                    dockerfile {
                        filename ' ./Dockerfile'
                        args "-u root -v D:\jenkins:/var/jenkins_home"
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
