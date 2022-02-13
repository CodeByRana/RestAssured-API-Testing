package RestAssuredSpotify.Spotify;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SpotifyApi {
	
	String token = "";
	String userId;
	
	@BeforeTest
	public void setup() {
		token = "Bearer BQAmJLVBX0dPCK3zv3myXGmI-CyVdinSNTRNUI_lK7AnlE1efPam-7BoEFc6Ya7u5CBv_ffVjXms3wD0CAbrvktQH6syfDJUtTlMdtKzMQ0_PaCiyDzpDarxgsUDaynRnTbC7Qgy-EuTq4cYEv6uTZSSNnDe9ql-plpM1MJloEY_PkQrwyPC6TomSl1SINupOD_wSfQgT-Si0md3XBuly3YVXfe4RWeV_SyBkTxITxJrHiaFOtg1V-xKoCdKq_aFkovtsOIB64tuVQlTPns";
	}
	
	@Test
	public void testGET_CurrentUserProfile_ShouldReturn_StatusCode200() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("status code is : " +statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void testGET_CurrentUserProfileId_ShouldReturn_StatusCode200() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.get("https://api.spotify.com/v1/me");
		userId = response.then().extract().path("id");
		System.out.println("User Id:" +userId);
		response.prettyPrint();
		response.then().statusCode(200);
	}
	@Test
	public void testPOST_When_CreateAPlaylist_should_ReturnStatusCode201() {
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", "My Playlist");
		requestParam.put("description" , "Second playlist description");
		requestParam.put("public", "false");
		httpRequest.header("Authorization", token);
		httpRequest.body(requestParam.toString());
		Response response = httpRequest.request(Method.POST, "https://api.spotify.com/v1/users/31ss5unrjygeqtgzoir22gplqo5m/playlists");
		String responseBody = response.getBody().asString();
		System.out.println("response body is "+responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("status code is :"+statusCode);
		Assert.assertEquals(statusCode, 201);
	}
	@Test
	public void testUpdate_When_CreateAPlaylist_Sholuld_ReturnStatusCode201() {
		
	}
}
