pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            // Reuse the Maven local repository from the host to speed up builds
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Environment Check') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Run CRUD Tests') {
            steps {
                script {
                    try {
                        sh 'mvn test -DsuiteXmlFile=testng-apitestcrud.xml'
                    } finally {
                        // Preserve the report for this specific suite
                        sh 'mkdir -p target/final-reports'
                        sh 'cp target/report/extent-report.html target/final-reports/crud-extent-report.html || true'
                    }
                }
            }
        }

        stage('Run Auth Tests') {
            steps {
                script {
                    try {
                        sh 'mvn test -DsuiteXmlFile=testng-authApiTestCrud.xml'
                    } finally {
                        // Preserve the report for this specific suite
                        sh 'mkdir -p target/final-reports'
                        sh 'cp target/report/extent-report.html target/final-reports/auth-extent-report.html || true'
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
