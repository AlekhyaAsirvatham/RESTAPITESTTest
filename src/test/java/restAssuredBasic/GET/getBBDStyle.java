package restAssuredBasic.GET;

import io.restassured.RestAssured;

public class getBBDStyle {
    public static void main(String[] args){
        //Gherkings Syntax

        //Given()-> url,header, auth, body
        //When()-> POST,PUT,GET
        //Then()->200 ok, validate response

        //FULL UrL="https://api.zippopotam.us/IN/506002"
        //BASE URL=https://api.zippopotam.us
        //PATH URL=IN/506002

        String pincode="506002";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode)
                .when().log().all().get()
                .then().log().all().statusCode(200);


        pincode="-1";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("IN/"+pincode)
                .when().log().all().get()
                .then().log().all().statusCode(404);
    }
}
