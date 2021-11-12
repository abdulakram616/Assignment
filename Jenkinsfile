pipeline {
    agent { docker { image '3.11.0a2-bullseye' } }
      stages {
        stage('log version info') {
      steps {
        sh 'python --version'
        sh 'python clean install'
      }
    }
  }
}
