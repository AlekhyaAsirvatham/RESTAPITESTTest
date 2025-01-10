package restAssuredBasic.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class getNonBDDStyle {

    RequestSpecification r;
    Response response;
    ValidatableResponse ss;

    @Description("verify the positive")

    @Test
    public void test1nonBddPositive(){
        String pincode="506002";

        r= RestAssured
                .given();

               r. baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode);
                       response= r.when().log().all().get();

                ss=response.then().log().all().statusCode(200);


    }

    @Test
    public void test1nonBddNegative(){
        String pincode="-1";
        r= RestAssured
                .given();

        r. baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode);
        response= r.when().log().all().get();

        ss=response.then().log().all().statusCode(200);


    }
    @Test
    public void test1nonBddNegative2(){
        String pincode="100000000000";
        r= RestAssured
                .given();

        r. baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode);
        response= r.when().log().all().get();

        ss=response.then().log().all().statusCode(200);


    }
}
