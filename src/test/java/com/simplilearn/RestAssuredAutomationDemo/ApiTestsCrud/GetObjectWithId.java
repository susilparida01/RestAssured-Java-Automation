package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class GetObjectWithId {
	
	@Test
	public void testGetObjectWithId() {
		
		Response getResponse = 
				given()
					.baseUri("https://api.restful-api.dev")
				.when()
					.get("/objects/9")
				.then()
					.statusCode(200)
					.body("id", equalTo("9"))
					.body("name", equalTo("Beats Studio3 Wireless"))
					.extract()
					.response();
		
		System.out.println("GET /objects/  response");
		getResponse.prettyPrint();
	}

}
