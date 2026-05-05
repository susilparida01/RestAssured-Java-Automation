package com.simplilearn.RestAssuredAutomationDemo.ApiTestsCrud;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateObject extends BaseApiTest {
	
	@Test
	public void testUpdateObject() {
		
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
	}

}
