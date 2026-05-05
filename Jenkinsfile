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
                bat """
                docker run --rm -v %cd%:/app %IMAGE_NAME% mvn test -DsuiteXmlFile=testng-apitestcrud.xml
                """
            }
        }

        stage('Run Auth Tests') {
            steps {
                bat """
                docker run --rm -v %cd%:/app %IMAGE_NAME% mvn test -DsuiteXmlFile=testng-authApiTestCrud.xml
                """
            }
        }
    }

    post {
        always {
            // Safely publish TestNG/Surefire results
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true

            // Archive HTML reports (Extent reports if present)
            archiveArtifacts artifacts: 'target/**/*.html', allowEmptyArchive: true

            // Clean workspace
            cleanWs()
        }
    }
}