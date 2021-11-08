pipeline {
	agent { 
		dockerfile true
	}
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
