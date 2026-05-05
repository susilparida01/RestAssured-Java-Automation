# RestAssured Automation Demo

This repository contains an automated testing framework for REST APIs using **Java**, **Rest-Assured**, and **TestNG**. It demonstrates CRUD operations and authentication-based API testing with structured reporting.

## 🚀 Tech Stack

*   **Language:** Java 21
*   **API Testing Library:** Rest-Assured 6.0.0
*   **Testing Framework:** TestNG 7.12.0
*   **Reporting:** ExtentReports 5.1.2
*   **Build Tool:** Maven

## 📂 Project Structure

```text
RestAssuredAutomationDemo/
├── src/main/java/com/simplilearn/RestAssuredAutomationDemo/
│   └── reporting/             # Extent Report Management & Listeners
├── src/test/java/com/simplilearn/RestAssuredAutomationDemo/
│   ├── ApiTestsCrud/         # Basic CRUD API Tests (Get, Post, Put, Delete)
│   └── authApiTestsCrud/     # Authenticated API Tests
├── src/test/resources/
│   └── config.properties     # API Base URL and Keys
├── testng-apitestcrud.xml     # Suite for basic CRUD tests
├── testng-authApiTestCrud.xml # Suite for authenticated tests
└── pom.xml                    # Project dependencies and build config
```

## 🛠 Prerequisites

*   **Java Development Kit (JDK) 21** or higher.
*   **Apache Maven** installed.
*   An IDE (IntelliJ IDEA, Eclipse, or VS Code).

## 🏃 Running Tests

### From Command Line
You can run specific test suites using the following Maven commands:

**Run CRUD Tests:**
```bash
mvn clean test -DsuiteXmlFile=testng-apitestcrud.xml
```

**Run Authenticated API Tests:**
```bash
mvn clean test -DsuiteXmlFile=testng-authApiTestCrud.xml
```

### From IDE
1.  Right-click on either `testng-apitestcrud.xml` or `testng-authApiTestCrud.xml`.
2.  Select **Run '...xml'**.

## 📊 Reporting

The framework uses **ExtentReports** for visual test results. After execution, reports are generated in the `target/report` directory:

*   Open `target/report/extent-report.html` in any web browser to view the detailed results, including pass/fail status and logs.

## ⚙️ Configuration

API configurations such as `baseURI` are managed in `src/test/resources/config.properties`.

```properties
baseURI=https://api.restful-api.dev
x-api-key=your-api-key-here
```
