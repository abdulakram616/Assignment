pipeline {
    agent {
        docker {
            image '<image name>'
            registryUrl 'https://registry.hub.docker.com'
            registryCredentialsId 'docker-credentials'
            args '--network host -u root:root'
        }
    }

    stages {
        stage('Test') {
            steps {
                sh 'python jenkins_pipeline_scripts/scripts/test.py'   
            }
        }
    }
}
