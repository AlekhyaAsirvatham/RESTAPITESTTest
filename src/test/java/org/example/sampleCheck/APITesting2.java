package org.example.sampleCheck;

import io.restassured.RestAssured;

public class APITesting2 {

    public static void main(String[] args){
       //Gherkings Syntax

        //Given()-> url,header, auth, body
        //When()-> POST,PUT,GET
        //Then()->200 ok, validate response

        //FULL UrL="https://api.zippopotam.us/IN/506002"
        //BASE URL=https://api.zippopotam.us
        //PATH URL=IN/506002

        RestAssured
                .given()
                  .baseUri("https://api.zippopotam.us")
                   .basePath("IN/506002")
                .when()
                   .get()
                .then()
                    .log().all().statusCode(200);
    }
}
