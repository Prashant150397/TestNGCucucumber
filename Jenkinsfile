pipeline {
    agent any

    tools {
        jdk 'JDK17'        // must match Jenkins Global Tool name
        maven 'Maven'      // must match Jenkins Maven tool name
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Prashant150397/TestNGCucucumber.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                echo 'Publishing Cucumber Report'
            }
            post {
                always {
                    cucumber(
                        buildStatus: 'UNSTABLE',
                        jsonReportDirectory: 'target/'
                    )
                }
            }
        }

        stage('Deploy') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                echo 'Deploy step (placeholder)'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed'
        }
        failure {
            echo 'Pipeline failed ❌'
        }
        success {
            echo 'Pipeline succeeded ✅'
        }
    }
}
