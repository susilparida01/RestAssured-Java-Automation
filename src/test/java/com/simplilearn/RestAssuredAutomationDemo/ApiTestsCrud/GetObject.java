package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetObject extends BaseApiTest{
	
	@Test
	public void testGetObject() {
		
		Assert.assertNotNull(objectId, "Object Id should be populated by postobject");
		
		
		Response getResponse = 
				given()
					//.baseUri("https://api.restful-api.dev")
					.accept(ContentType.JSON)				
				.when()
					.get("/objects/" + objectId)
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Get /objects/" + objectId + " response");
		getResponse.prettyPrint();
	}

}
