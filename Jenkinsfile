pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'Build job Started'
				//dir ("${env.WORKSPACE}"/m2_vf_dc-0b_MM_update/BSW_and_TestCode/Project_Build/Batchfiles) {
				 //    bat 'dir'
                   //  bat 'python build_M2_Inverter.py'
				//}
				bat """
                    helloworld.py
				"""
	    }
	}		
    }
}
