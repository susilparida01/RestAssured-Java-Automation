package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class GetObjectsByIds extends BaseApiTest {
	
	@Test
	public void testGetObjectsByIds() {
		
		//please code
		Response getResponse = 
				
				given()				
				.when()
					.get("/objects?id=3&id=5&id=7")
				
				.then()
					.statusCode(200)
					.extract()
					.response();
		
		System.out.println("Get /objects/ response");
		getResponse.prettyPrint();
	}

}
