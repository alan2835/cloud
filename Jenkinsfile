pipeline {
    agent any
	
	tools{
		maven 'Maven3.5.3'
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
			when {
				branch "master"
			}
            steps {
                sh 'mvn -B clean verify'
            }
        }
		
		stage ('SonarQube Analysis'){
			steps{
				dir("project_templates/java_project_template"){
					withSonarQubeEnv('SonarQube5.3') {
						sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
					}
				}
			}
		}
		
		stage ('Artifactory Deploy'){
			when {
				branch "master"
			}
			steps{
				dir("project_templates/java_project_template"){
					script {
						def server = Artifactory.server('artifactory')
						def rtMaven = Artifactory.newMavenBuild()
						rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
						rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
						rtMaven.tool = 'maven 3'
						def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install'
						server.publishBuildInfo buildInfo
					}
				}
			}
		}
    }
		
	/*post {
		success {
			slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})") 
		}
		failure {
			slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})") 
		}
	}*/
}