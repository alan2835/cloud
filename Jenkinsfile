pipeline {
    agent any
	
	tools{
		maven 'maven3.5.3'
		jdk 'Java8'
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