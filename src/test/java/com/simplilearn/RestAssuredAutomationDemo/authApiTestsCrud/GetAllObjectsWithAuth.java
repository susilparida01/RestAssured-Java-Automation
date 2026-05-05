package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllObjectsWithAuth extends BaseApiTest {
	
	@Test
	public void testGetAllObjects() {
		
		//Authenticate the API
		String apiKey = prop.getProperty("x-api-key");
		
		// Code
		Response getResponse = 
				given()
					.header("x-api-key", apiKey)
					.accept(ContentType.JSON)
				.when()
					.get("/collections/products/objects")
				.then()
					.statusCode(200)
					//.body("name", equalTo("Apple iPad Mini 5th Gen"))
					.extract()
					.response();
		
		logInfo("Successfully retrieve all objects");
		System.out.println("GET /objects/  response");
		getResponse.prettyPrint();
				
	}

}
