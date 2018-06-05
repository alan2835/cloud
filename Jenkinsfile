pipeline {
    agent any
	
	tools{
		maven 'Maven3.5.3'
		jdk 'Java8'
	}
	
   /* parameters {
        string(name: 'PROJECT', defaultValue: 'RIC Project', description: 'What is this project about')
    } */
	
    stages {
        stage('initialize') {
            steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
				'''
            }
        }
		
        stage('Build') {
            steps {
                sh 'mvn -B clean verify'
            }
        }
		
		stage ('SonarQube Analysis'){
			steps{
				sh '''
					mvn sonar:sonar \
						-Dsonar.host.url=http://192.168.40.128:9000 \
						-Dsonar.login=bf56bf0e765277ac1029d70745152b16fa951aa7
				'''
			}
		}
		
		
	/*	stage ('SonarQube Analysis'){
			when {
				branch "master"
			}
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
		} */
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