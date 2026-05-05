pipeline {
    agent any

    environment {
        IMAGE_NAME = "restassured-automation-demo"
    }

    stages {
        stage('Docker Build') {
            steps {
                bat "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Environment Check') {
            agent {
                docker {
                    image "${IMAGE_NAME}"
                    args '-v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run CRUD Tests') {
            agent {
                docker {
                    image "${IMAGE_NAME}"
                    args '-v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                script {
                    try {
                        bat 'mvn test -DsuiteXmlFile=testng-apitestcrud.xml'
                    } finally {
                        // Preserve the report for this specific suite
                        bat 'mkdir -p target/final-reports'
                        bat 'cp target/report/extent-report.html target/final-reports/crud-extent-report.html || true'
                    }
                }
            }
        }

        stage('Run Auth Tests') {
            agent {
                docker {
                    image "${IMAGE_NAME}"
                    args '-v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                script {
                    try {
                        bat 'mvn test -DsuiteXmlFile=testng-authApiTestCrud.xml'
                    } finally {
                        // Preserve the report for this specific suite
                        bat 'mkdir -p target/final-reports'
                        bat 'cp target/report/extent-report.html target/final-reports/auth-extent-report.html || true'
                    }
                }
            }
        }
    }

    post {
        always {
            // Publish TestNG results (requires TestNG Results Plugin)
            testng testResults: '**/target/surefire-reports/testng-results.xml'
            
            // Archive both Extent Reports as artifacts
            archiveArtifacts artifacts: 'target/final-reports/*.html', allowEmptyArchive: true
            
            // Clean up workspace (optional)
            cleanWs()
        }
    }
}
