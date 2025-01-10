package payLoadManagement.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class gsonDemo {

    RequestSpecification rs;
    ValidatableResponse vs;


    @Test
    public void gsonTest(){
        //STEP_1
        //URL
        //Header
        //Body
        //Auth

        //STEP_2

        //prepare the pay laod(object to json)
        //send the request



        //STEP_3
        //validte Response=(JSON-> Object)

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

        System.out.println(booking);

        //java -> Json to send

        Gson gs=new Gson();
       String payload= gs.toJson(booking);
        System.out.println("jsonStringBooking="+payload);

        RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON).log().all();
        rs.body(payload);


        Response r1 = rs.when().log().all().post();

        ValidatableResponse v = r1.then().log().all();
        v.statusCode(200);



        //jsonpath to extract

        JsonPath jsonPath=new JsonPath(r1.asString());
        String bookingId=jsonPath.getString("bookingid");
        String firstname=jsonPath.getString("booking.firstname");


        System.out.println("bookingId="+bookingId);

        System.out.println("firstname="+firstname);



        //Deserialization extraction


       BookingResponse bookingResponse=gs.fromJson(r1.asString(),BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());

       assertThat(bookingResponse.getBooking().getFirstname().equals("Alekhya"));





















    }
}
