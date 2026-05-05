package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseApiTest {
	
	protected Properties prop;
	protected static String objectId;
	
	@BeforeClass
	public void setup() {
		//Base URI for Restful API
		RestAssured.baseURI = "https://api.restful-api.dev";
		
		// Log all requests and responses to console
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
	}

}
