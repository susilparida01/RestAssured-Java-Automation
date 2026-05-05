package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class GetAllObjects {
	
	@Test
	public void testGetAllObjects() {
		// Code
		Response getResponse = 
				given()
					.baseUri("https://api.restful-api.dev")
				.when()
					.get("/objects")
				.then()
					.statusCode(200)
					//.body("name", equalTo("Apple iPad Mini 5th Gen"))
					.extract()
					.response();
		
		System.out.println("GET /objects/  response");
		getResponse.prettyPrint();
				
	}

}
