pipeline {
    agent any
	
	tools{
		maven 'Maven3.5.3'
		jdk 'Java8'
	}
	
    stages {
        stage('initialize') {
            steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
				'''
            }
        }
		
        stage('Compile') {
            steps {
                sh 'mvn -B clean compile'
            }
        }
		
        stage('Build') {
            steps {
                sh 'mvn -B clean verify'
            }
        }
		
		stage ('SonarQube Analysis'){
			when {
				branch "master"
			}
			steps{
				sh '''
					mvn sonar:sonar \
						-Dsonar.host.url=http://192.168.40.128:9000 \
						-Dsonar.login=e974bbf96bff04f5a39ad39aff3134e4bf886031
				'''
			}
		}
		
		stage('Results') {
			steps{
				junit 'target/surefire-reports/TEST-*.xml'
				archive 'target/*.jar'
			}
		}
		
		stage('Publish') {
			when {
				branch "master"
			}
			
			steps{
				nexusPublisher \
					nexusInstanceId: 'repo', \
					nexusRepositoryId: 'releases', \
					packages: \
						[[$class: 'MavenPackage', \
							mavenAssetList: [[classifier: '', \
								extension: '', \
								filePath: 'target/cloud-0.0.1.jar']], \
							mavenCoordinate: [artifactId: 'cloud', \
								groupId: 'com.alan.ham', \
								packaging: 'jar', \
								version: '0.0.1']]]
			}
		}
	}
	
	post {
		success {
			slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})") 
		}
		failure {
			slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})") 
		}
	}
}	