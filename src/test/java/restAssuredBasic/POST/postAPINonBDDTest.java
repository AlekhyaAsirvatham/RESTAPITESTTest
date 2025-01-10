package restAssuredBasic.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class postAPINonBDDTest {

    @Test
    public void postNonBDD() {

        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RequestSpecification rs= RestAssured.given();
                rs.baseUri("https://restful-booker.herokuapp.com");
                rs.basePath("/auth");
                rs.contentType(ContentType.JSON).log().all();
                        rs.body(payload);


                Response r1=rs.when().log().all().post();

        ValidatableResponse v=r1.then().log().all().statusCode(200);



    }

}
