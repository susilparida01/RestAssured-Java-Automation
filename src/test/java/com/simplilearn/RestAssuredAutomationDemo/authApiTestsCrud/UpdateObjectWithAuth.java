package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateObjectWithAuth extends BaseApiTest{
	
	@Test
	public void testUpdateObject() {
		
		//Authenticate the API
		String apiKey = prop.getProperty("x-api-key");
		
		Assert.assertNotNull(objectId, "Object Id should be populated by postobject");
		
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
					.header("x-api-key", apiKey)
					.contentType(ContentType.JSON)
					.body(updatePayload)
				
				.when()
					.put("/collections/products/objects/" + objectId)
				
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
	}	

}
