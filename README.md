# RestAssured Automation Demo

This repository contains a robust, containerized automated testing framework for REST APIs using **Java**, **Rest-Assured**, and **TestNG**. It demonstrates CRUD operations, authentication-based API testing, dynamic extent reporting, and local/containerized test execution.

---

## 🚀 Tech Stack

*   **Language:** Java 21
*   **API Testing Library:** Rest-Assured 6.0.0
*   **Testing Framework:** TestNG 7.12.0
*   **Reporting:** ExtentReports 5.1.2 (via TestNG Listeners)
*   **Build Tool:** Maven
*   **Containerization:** Docker
*   **CI/CD Pipeline:** Jenkins (Declarative Pipeline)

---

## 📂 Project Structure

```text
RestAssuredAutomationDemo/
├── src/
│   ├── main/java/com/simplilearn/RestAssuredAutomationDemo/
│   │   └── reporting/             # Extent Report Management & TestNG Listeners
│   │       ├── ExtentReportListener.java  # Captures pass/fail events during suites
│   │       └── ExtentReportManager.java   # Configures spark-reporter & report paths
│   └── test/
│       ├── java/com/simplilearn/RestAssuredAutomationDemo/
│       │   ├── ApiTestsCrud/         # Unauthenticated Basic CRUD API Tests
│       │   │   ├── BaseApiTest.java  # Base configurations, logging, properties loader
│       │   │   └── ...               # Individual test classes (Post, Get, Put, Delete)
│       │   └── authApiTestsCrud/     # Authenticated API Tests (Header based)
│       │       ├── BaseApiTest.java  # Base configurations for auth-dependent API tests
│       │       └── ...               # Auth test scenarios (x-api-key driven)
│       └── resources/
│           └── config.properties     # API Endpoint Base URLs and authorization tokens
├── Dockerfile                     # Multi-stage Docker config for local & CI execution
├── Jenkinsfile                    # Jenkins CI/CD pipeline using Docker execution
├── testng-apitestcrud.xml         # XML Suite for basic CRUD tests
├── testng-authApiTestCrud.xml     # XML Suite for authenticated tests
└── pom.xml                        # Maven dependencies & plugins declaration
```

---

## 🛠 Prerequisites

Ensure you have the following installed on your machine:
*   **Java Development Kit (JDK) 21** or higher.
*   **Apache Maven** installed.
*   **Docker Desktop** (if running via containers).
*   An IDE (IntelliJ IDEA, Eclipse, or VS Code).

---

## 🏃 Running Tests Locally (Non-Containerized)

### 1. Configure the API Properties
Update your target Base URL and api-key details in [config.properties](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/src/test/resources/config.properties):
```properties
baseURI=https://api.restful-api.dev
x-api-key=your-api-key-here
```

### 2. Execution from Command Line
You can run specific TestNG suites via Maven commands:

*   **Run standard CRUD tests:**
    ```bash
    mvn clean test -DsuiteXmlFile=testng-apitestcrud.xml
    ```
*   **Run authenticated CRUD tests:**
    ```bash
    mvn clean test -DsuiteXmlFile=testng-authApiTestCrud.xml
    ```

### 3. Execution from IDE
1.  Right-click on [testng-apitestcrud.xml](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/testng-apitestcrud.xml) or [testng-authApiTestCrud.xml](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/testng-authApiTestCrud.xml).
2.  Select **Run '...xml'** or execution option in your IDE's TestNG runner.

---

## 🐳 Running with Docker (Containerized)

Docker containerization ensures consistent environments across local development and CI pipelines.

### 1. Understanding the Dockerfile
The project includes a custom [Dockerfile](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/Dockerfile) that prepares a JDK 21 and Maven execution workspace:
*   **Base Image**: `maven:3.9.6-eclipse-temurin-21` (Includes pre-installed Maven and JDK 21).
*   **Dependency Caching**: Copies `pom.xml` and runs `mvn dependency:go-offline` to fetch dependencies first. This speeds up future builds because dependencies are cached inside the Docker layer.
*   **Execution**: Copies the full workspace and sets the default container startup command to `mvn test`.

### 2. Build the Docker Image
Build the Docker image locally by running this command in your project root:
```bash
docker build -t restassured-automation-demo .
```

### 3. Run Tests inside Docker (with volume mounting)
To execute tests containerized while mapping test results and visual reports back to your host machine's `target/` directory:

*   **On Windows (Command Prompt):**
    ```cmd
    docker run --rm -v %cd%:/app restassured-automation-demo mvn test -DsuiteXmlFile=testng-apitestcrud.xml
    ```
*   **On Windows (PowerShell):**
    ```powershell
    docker run --rm -v ${PWD}:/app restassured-automation-demo mvn test -DsuiteXmlFile=testng-apitestcrud.xml
    ```
*   **On Linux / macOS (Terminal):**
    ```bash
    docker run --rm -v $(pwd):/app restassured-automation-demo mvn test -DsuiteXmlFile=testng-apitestcrud.xml
    ```

> [!NOTE]
> The `-v` parameter maps your host directory to `/app` inside the container. This ensures that the generated Extent Reports (`target/report/extent-report.html`) and XML Test Results are saved locally for analysis.

---

## 🔗 CI/CD Pipeline Integration (Jenkins)

The project includes a declarative [Jenkinsfile](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/Jenkinsfile) which automatically builds the Docker environment and runs test suites inside ephemeral Docker containers.

### 1. Breakdown of Pipeline Stages
*   **Docker Build:** Builds the Docker image labeled `restassured-automation-demo` dynamically.
*   **Environment Check:** Verifies JDK and Maven versions inside the built image before executing tests.
*   **Run CRUD Tests:** Executes the standard CRUD operations test suite inside the container.
*   **Run Auth Tests:** Runs the authenticated API tests inside the container.
*   **Post Execution (`always`):**
    *   Publishes JUnit results from XML reports (`**/target/surefire-reports/*.xml`).
    *   Archives the Extent HTML visual report (`target/**/*.html`).
    *   Runs `cleanWs()` to ensure the Jenkins agent workspace is cleaned up for subsequent runs.

### 2. How to Setup the Jenkins Job
1.  **Install Plugins**: Ensure the **JUnit**, **HTML Publisher**, and **Docker Pipeline** plugins are installed on your Jenkins instance.
2.  **Create Pipeline Job**: Go to Jenkins Home > **New Item** > Select **Pipeline**.
3.  **Pipeline Definition**:
    *   Under *Pipeline*, select **Pipeline script from SCM**.
    *   Choose **Git** and supply this repository URL.
    *   Set the branch path (e.g., `*/main`).
    *   Verify the *Script Path* points to `Jenkinsfile`.
4.  **Run Pipeline**: Click **Build Now**. The pipeline will run all test suites containerized and archive visual and status reports in the build history.

---

## 📊 Reporting & Logging

Visual HTML test reports are compiled using **ExtentReports** after every execution:
*   **Reports Path:** `target/report/extent-report.html` (viewable in any web browser).
*   **How it Works:** The framework leverages TestNG listeners ([ExtentReportListener.java](file:///D:/Susil-Study/SimpliLearnGLVC/RestAssuredAutomationDemo/src/main/java/com/simplilearn/RestAssuredAutomationDemo/reporting/ExtentReportListener.java)), which hook into test executions to log passes, failures, and console parameters automatically.

---

## 🎓 Interview Q&A: Presenting this Project

If you are asked about this project in a job interview, here is how you should pitch it and answer key questions:

### 1. High-Level Elevator Pitch
> *"I built a scalable API Test Automation Framework using Java, Rest-Assured, and TestNG. The framework is designed for containerized executions via Docker and integrates seamlessly into a CI/CD pipeline using a Declarative Jenkinsfile. It handles basic CRUD operations and key-based authentication, generating visual report artifacts via ExtentReports listeners. Everything is modularized, allowing test configurations to be updated easily via property files."*

### 2. Potential Interview Questions & Answers

#### Q: Why did you use Docker in your test automation framework?
**A:** Docker ensures consistency. Instead of installing JDK 21, Maven, and dependencies directly on the local machine or Jenkins agent, I created a Dockerfile. The container spins up with the exact environment configured, eliminating the "works on my machine" problem. Volume mounting is used to extract HTML and JUnit XML reports from the container back to the host filesystem.

#### Q: How does the Jenkins pipeline run tests containerized?
**A:** The Jenkins pipeline builds the Docker image first. Then, in the execution stages, it uses `docker run` combined with volume mounting (`-v %cd%:/app`) to mount the workspace directory. When Maven runs the TestNG suite inside the container, the generated files (`target/`) are written directly to the Jenkins workspace on the host. This allows Jenkins to successfully archive the Extent HTML reports and publish JUnit test metrics in the post-execution steps.

#### Q: Explain how reporting is handled in this project.
**A:** I implemented dynamic reporting using an `ITestListener` from TestNG (`ExtentReportListener`). Instead of manually calling report logs in every test method, the listener hooks into test events (`onTestStart`, `onTestSuccess`, `onTestFailure`). It automatically instantiates `ExtentReports` via a ThreadLocal utility to ensure thread safety during parallel runs, writing HTML logs to the `target/report/extent-report.html` file.

#### Q: How are configurations and authorization keys managed?
**A:** Configurations like the API base URL (`baseURI`) and authorization credentials (`x-api-key`) are externalized in a `config.properties` file located in the resources directory. The base test class (`BaseApiTest`) loads these properties in the `@BeforeClass` setup phase so they are instantly accessible to all test methods.
