package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostObjectWithAuth extends BaseApiTest{
	
	@Test
	public void testPostObjectWithAuth() {
		
		//Authenticate the API
		String apiKey = prop.getProperty("x-api-key");
		
		String createPayload = """
				{
				  "name": "Apple MacBook Pro 16",
				  "data": {
				    "year": 2019,
				    "price": 1849.99,
				    "CPU model": "Intel Core i9",
				    "Hard disk size": "1 TB"
				  }
				}		
				
				""";
		
		//Please code it here  
		Response postResponse =
				given()
					.header("x-api-key", apiKey)
					.contentType(ContentType.JSON)
					.body(createPayload)				
				
				.when()
					.post("/collections/products/objects")				
				
				.then()
					.statusCode(200)
					.header("Content-Type", containsString("application/json"))
					.body("name", equalTo("Apple MacBook Pro 16"))
					.body("data.price", equalTo(1849.99f))
					.extract()
					.response();
		
		System.out.println("POST /objects response");
		postResponse.prettyPrint();
		
		//Extract Created ID
		objectId = postResponse.jsonPath().getString("id");
		System.out.println("Stored object id for subsequent tests: " + objectId);
	}	

}
