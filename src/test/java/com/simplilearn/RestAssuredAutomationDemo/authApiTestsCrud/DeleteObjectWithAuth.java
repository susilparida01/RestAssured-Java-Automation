package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class DeleteObjectWithAuth extends BaseApiTest{
	
	@Test
	public void testDeleteObjectWithAuth() {
		
		//Authenticate the API
		String apiKey = prop.getProperty("x-api-key");			
		
		Assert.assertNotNull(objectId, "Object Id should be populated by postobject");
		
		Response deleteResponse =
				
				given()
					.header("x-api-key", apiKey)
				
				.when()
					.delete("/collections/products/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Delete /objects response");
		deleteResponse.prettyPrint();
	}	

}
