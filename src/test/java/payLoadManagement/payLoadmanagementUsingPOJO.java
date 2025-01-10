package payLoadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class payLoadmanagementUsingPOJO {


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











        Booking booking=new Booking();
         booking.setFirstname("Alekhya");
         booking.setLastname("All");
         booking.setTotalprice(111);
         booking.setDepositpaid(true);


         Bookingdates bookingdates=new Bookingdates();
         bookingdates.setCheckin("2018-01-01");
         bookingdates.setCheckout("2019-01-01");

         booking.setBookingdates(bookingdates);
         booking.setAdditionalneeds("Breakfast");



        RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON).log().all();
        rs.body(booking);


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

