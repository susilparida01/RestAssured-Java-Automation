pipeline {
    agent any

    environment {
        IMAGE_NAME = "restassured-automation-demo"
    }

    stages {

        stage('Docker Build') {
            steps {
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Environment Check') {
            steps {
                bat """
                docker run --rm %IMAGE_NAME% java -version
                docker run --rm %IMAGE_NAME% mvn -version
                """
            }
        }

        stage('Run CRUD Tests') {
            steps {
                script {
                    try {
                        bat """
                        docker run --rm %IMAGE_NAME% mvn test -DsuiteXmlFile=testng-apitestcrud.xml
                        """
                    } finally {
                        bat """
                        if not exist target\\final-reports mkdir target\\final-reports
                        """
                    }
                }
            }
        }

        stage('Run Auth Tests') {
            steps {
                script {
                    try {
                        bat """
                        docker run --rm %IMAGE_NAME% mvn test -DsuiteXmlFile=testng-authApiTestCrud.xml
                        """
                    } finally {
                        bat """
                        if not exist target\\final-reports mkdir target\\final-reports
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            // Use junit instead of testng
            junit '**/target/surefire-reports/*.xml'

            // Archive reports
            archiveArtifacts artifacts: 'target/**/*.html', allowEmptyArchive: true

            cleanWs()
        }
    }
}