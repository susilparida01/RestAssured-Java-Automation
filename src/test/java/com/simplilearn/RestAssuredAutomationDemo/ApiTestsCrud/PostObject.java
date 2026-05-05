package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class PostObject extends BaseApiTest{
	
	@Test
	public void testPostObject() {
		
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
					//.baseUri("https://api.restful-api.dev")
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
	}

}
