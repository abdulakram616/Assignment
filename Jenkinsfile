pipeline {
    agent any
    stages {
        stage('build') {
            agent { docker { image '4246fb19839f' } }
            steps {
                sh 'python --version'
            }
        }
    }
}
