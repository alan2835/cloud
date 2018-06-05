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
						-Dsonar.login=bf56bf0e765277ac1029d70745152b16fa951aa7
				'''
			}
		}
		
		stage('Results') {
			junit '**/target/surefire-reports/TEST-*.xml'
			archive 'target/*.jar'
		}
		
		stage('Publish') {
			when {
				branch "master"
			}
			
			nexusPublisher \
				nexusInstanceId: 'repo', \
				nexusRepositoryId: 'releases', \
				packages: \
					[[$class: 'MavenPackage', \
						mavenAssetList: [[classifier: '', \
							extension: '', \
							filePath: 'target/cloud-0.0.1-SNAPSHOT.jar']], \
						mavenCoordinate: [artifactId: 'cloud', \
							groupId: 'com.alan.ham', \
							packaging: 'jar', \
							version: '0.0.1-SNAPSHOT']]]
	}
		
	/*	
		stage ('Artifactory Deploy'){
			when {
				branch "master"
			}
			
    steps {
      nexusArtifactUploader {
        nexusVersion('nexus2')
        protocol('http')
        nexusUrl('192.168.40.128:8081/nexus')
        groupId('sp.sd')
        version('2.4')
        repository('NexusArtifactUploader')
        credentialsId('44620c50-1589-4617-a677-7563985e46e1')
        artifact {
            artifactId('nexus-artifact-uploader')
            type('jar')
            classifier('debug')
            file('nexus-artifact-uploader.jar')
        }
        artifact {
            artifactId('nexus-artifact-uploader')
            type('hpi')
            classifier('debug')
            file('nexus-artifact-uploader.hpi')
        }
	}
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
	}*/
}