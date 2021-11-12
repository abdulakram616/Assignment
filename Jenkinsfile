pipeline {
    agent { docker { image 'python' } }
      stages {
        stage('log version info') {
      steps {
        sh 'python --version'
        sh 'python clean install'
      }
    }
  }
}
