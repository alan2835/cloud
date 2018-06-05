pipeline {
    agent any
	
	tools{
		maven 'maven 3'
		jdk 'java 8'
	}
	
    parameters {
        string(name: 'RIC Project A', defaultValue: 'RIC Project', description: 'What is this project about')
    }
    stages {
	
        stage('initialize') {
            steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
					echo "Hello ${params.name}"
				'''
            }
        }
		
        stage('Build ${params.name}') {
            steps {
                sh 'mvn -B clean verify'
            }
        }
		
    }
}