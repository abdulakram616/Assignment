pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'Build job Started'
				
				bat """
                    helloworld.py
				"""
	    }
	}		
    }
}
