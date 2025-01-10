package payLoadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class payLoadApimanagement {

    @Test
    public void payLoadUsingMap() {


     /*   String payLoad = "{\n" +
                "    \"firstname\" : \"Alekhya\",\n" +
                "    \"lastname\" : \"lastname\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        */


        //using MAP

        Map<String, Object> jsonmap=new LinkedHashMap<>();

        jsonmap.put("firstname","Alekhya");
        jsonmap.put("lastname","lastname");
        jsonmap.put("totalprice","111");
        jsonmap.put("depositpaid","true");


        Map<String, Object> bookingDates=new LinkedHashMap<>();

        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");



        jsonmap.put("bookingdates",bookingDates);
        jsonmap.put("additionalneeds","Breakfast");


        System.out.println(jsonmap);








        //MAP->JSOn is done by GSON,JACKSON API



       RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON).log().all();
        rs.body(jsonmap);


        Response r1 = rs.when().log().all().post();

        ValidatableResponse v = r1.then().log().all();
        v.statusCode(200);
        v.body("bookingid", Matchers.notNullValue());
        v.body("booking.firstname",Matchers.equalTo("Alekhya"));

       int bookingId=r1.then().extract().path("bookingid");
        String firstName=r1.then().extract().path("booking.firstname");

        //Assertion using testng


        Assert.assertEquals(firstName,"Alekhya");
        Assert.assertNotNull(bookingId);


        //Assertion using AssertJ

        assertThat(bookingId).isNotNull().isNotZero().isPositive();
    }
}
