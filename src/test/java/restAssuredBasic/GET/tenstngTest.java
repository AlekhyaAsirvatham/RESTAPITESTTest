package restAssuredBasic.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class tenstngTest {

    @Test
    public void test1(){
        String pincode="506002";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode)
                .when().log().all().get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test2(){
        String pincode="-1";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode)
                .when().log().all().get()
                .then().log().all().statusCode(404);
    }


}
