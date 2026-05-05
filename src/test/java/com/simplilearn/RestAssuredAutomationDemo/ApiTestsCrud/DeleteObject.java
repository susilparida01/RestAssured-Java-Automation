package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteObject extends BaseApiTest{
	
	@Test
	public void testDeleteObject() {
		
		Assert.assertNotNull(objectId, "Object Id should be populated by postobject");
		
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
