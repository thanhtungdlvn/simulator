pipeline {
	agent any

	options {
	    buildDiscarder(logRotator(artifactNumToKeepStr: '5', numToKeepStr: '20'))
	}

	environment {
		mavenHome = tool 'jenkins-maven'

		SONAR_URL='http://localhost:9090'
        SONAR_LOGIN='sqp_08c7914d266a1a2f88a3116d81c8505947f2c5f2'
	}

	tools {
		jdk 'java-17'
	}

	stages {

		stage('Build'){
			steps {
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				bat "mvn test"
			}
		}

		stage('Code Analysis'){
		    steps {
		        echo "Run the code analysis"
                bat "mvn clean verify sonar:sonar -Dsonar.projectKey=simulator -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_LOGIN"
		    }
		}
		stage('Publish Report'){
		    steps {
		        echo "Publish Karate Report"
		        publishHTML (target: [
                      allowMissing: false,
                      alwaysLinkToLastBuild: false,
                      keepAll: true,
                      reportDir: './target/karate-reports',
                      reportFiles: 'karate-summary.html',
                      reportName: "Karate Report"
                    ])
		    }
		}
	}
}