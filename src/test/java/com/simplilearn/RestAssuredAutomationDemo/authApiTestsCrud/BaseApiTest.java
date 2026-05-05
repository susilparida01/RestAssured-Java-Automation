package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.Status;
import com.simplilearn.RestAssuredAutomationDemo.reporting.ExtentReportListener;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseApiTest {
	
	protected Properties prop;
	protected static String objectId;
	protected Logger log = Logger.getLogger(this.getClass());
	
	
	public void logInfo(String message) {
		log.info(message);
		if (ExtentReportListener.getTest() != null) {
			ExtentReportListener.getTest().log(Status.INFO, message);		
			
		}
	}
	
	
	@BeforeClass
	public void setup() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		prop.load(fis);	
		
		//Initialize RestAssured Base URI from config.properties
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		// Log all requests and responses to console
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		
		log.info("Initialized RestAssured with Base URI: "+ RestAssured.baseURI);		
		
	}
	
	@AfterClass
	public void tearDown() {
		
		log.info("Test Class Execution Completed");
	}

}










