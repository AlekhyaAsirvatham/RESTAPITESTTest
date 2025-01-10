package Assertion;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import static org.assertj.core.api.Assertions.*;


public class RestAssuredAssertion {


    @Test
    public void post(){


        int bookingId;

        String payLoad="{\n" +
                "    \"firstname\" : \"Alekhya\",\n" +
                "    \"lastname\" : \"Brown1\",\n" +
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
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON).log().all();
        rs.body(payLoad);


        Response r1=rs.when().log().all().post();

        ValidatableResponse v=r1.then().log().all();
                v.statusCode(200);

                v.body("bookingid",Matchers.notNullValue());
                v.body("booking.firstname",Matchers.equalTo("Alekhya"));

                 bookingId=r1.then().extract().path("bookingid");
                String firstName=r1.then().extract().path("booking.firstname");

                //Assertion using testng


                Assert.assertEquals(firstName,"Alekhyaa");
                Assert.assertNotNull(bookingId);


                //Assertion using AssertJ

               assertThat(bookingId).isNotNull().isNotZero().isPositive();
               assertThat(firstName).isEqualTo("Alekhya").isAlphabetic();
    }
}
