pipeline {
    agent {
        docker {
            image 'maven:3-jdk-8'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw --batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}