package com.simplilearn.RestAssuredAutomationDemo.authApiTestsCrud;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetObjectWithAuth extends BaseApiTest{
	
	@Test
	public void testGetObjectWithAuth() {
		
		//Authenticate the API
		String apiKey = prop.getProperty("x-api-key");		
		
		Assert.assertNotNull(objectId, "Object Id should be populated by postobject");
		
		
		Response getResponse = 
				given()
					.header("x-api-key", apiKey)
					.accept(ContentType.JSON)				
				.when()
					.get("/collections/products/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Get /objects/" + objectId + " response");
		getResponse.prettyPrint();
	}

}
