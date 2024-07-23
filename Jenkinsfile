pipeline {
	agent any

	environment {
		mavenHome = tool 'jenkins-maven'

		SONAR_URL='http://localhost:9000'
        SONAR_LOGIN='sqp_221a74787cd6d42675569f215a38dab8ae7aadb3'
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
	}
}