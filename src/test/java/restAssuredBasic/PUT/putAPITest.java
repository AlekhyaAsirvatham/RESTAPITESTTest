package restAssuredBasic.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class putAPITest {


    @Test
    public void putApi(){

        String token="46643abc387ce4e";
        String bookingId="1267";

        String payLoad="{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brownssnn\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification rs= RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking/"+bookingId);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
             rs.body(payLoad) .log().all();

        Response r1=rs.when().log().all().put();

        ValidatableResponse v=r1.then().log().all().statusCode(200);


    }
}
