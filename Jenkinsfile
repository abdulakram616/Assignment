pipeline {
	agent { label 'linux' }
    stages {
        stage('build') {
            steps {
                echo 'Build job Started'
				sh 'python helloworld.py'
				
	    }
	}		
    }
}

