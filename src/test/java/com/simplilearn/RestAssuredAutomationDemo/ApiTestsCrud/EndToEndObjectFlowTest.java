package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EndToEndObjectFlowTest extends BaseApiTest{
	
	@Test
	public void endToEndObjectFlowTest() {
		
		//1. Post - create an object
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
		
		//Please code it here  ff8081819d150699019d34fa82bb3cae
		Response postResponse =
				given()
					.contentType(ContentType.JSON)
					.body(createPayload)				
				
				.when()
					.post("/objects")				
				
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
		
		
		//2. GET - fetch the object
		Response getResponse = 
				given()
					.accept(ContentType.JSON)				
				.when()
					.get("/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Get /objects/" + objectId + " response");
		getResponse.prettyPrint();		
		
		//3. PUT - update the created object with new records		
		String updatePayload = """
				{
				  "name": "Apple MacBook Pro 20",
				  "data": {
				    "year": 2026,
				    "price": 9999.99,
				    "CPU model": "Intel Core i9",
				    "Hard disk size": "1 TB"
				  }
				}				
				
				""";
		
		
		Response updateResponse = 
				
				given()
					//.baseUri("https://api.restful-api.dev")
					.contentType(ContentType.JSON)
					.body(updatePayload)
				
				.when()
					.put("/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.header("Content-Type", containsString("application/json"))
					.body("name", equalTo("Apple MacBook Pro 20"))
					.body("data.year", equalTo(2026))
					.body("data.price", equalTo(9999.99f))					
					.extract()
					.response();
		
		System.out.println("Put /objects response");
		updateResponse.prettyPrint();	
		
		//4. DELETE - Delete the object
		Response deleteResponse =
				
				given()
					//.baseUri("https://api.restful-api.dev")
				
				.when()
					.delete("/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Delete /objects response");
		deleteResponse.prettyPrint();		
		
	}

}
