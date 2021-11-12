pipeline {
    agent { docker { image '3.10.0-alpine3.14' } }
      stages {
        stage('log version info') {
      steps {
        sh 'python --version'
        sh 'python clean install'
      }
    }
  }
}
