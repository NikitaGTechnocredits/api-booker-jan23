package bookerapi;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.Status_Code;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.CreateBookingRequest;

public class PartialUpdateBookingTest extends TestBase {

	@BeforeClass
	public void generateToken() {
		init();
	}
	
	@Test (priority = 6, enabled = false)
	public void partialUpdateBookingIdTest() {
		payload.setFirstname("Shashank");
		payload.setLastname("Garad");
		Response res = RestAssured.given()
				.headers("Content-Type", "application/json")
				.headers("Accept", "application/json")
				.headers("Cookie", "token="+token)
				.log().all()
				.body(payload)
				.when()
				.patch("/booking/"+bookingId);
		
		Assert.assertEquals(res.getStatusCode(), Status_Code.OK);
		Assert.assertTrue(res.as(CreateBookingRequest.class).equals(payload));
	}
}
