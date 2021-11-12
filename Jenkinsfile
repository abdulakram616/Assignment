pipeline {
    agent { docker { image '4246fb19839f' } }
      stages {
        stage('log version info') {
      steps {
        sh 'python --version'
        sh 'python clean install'
      }
    }
  }
}
