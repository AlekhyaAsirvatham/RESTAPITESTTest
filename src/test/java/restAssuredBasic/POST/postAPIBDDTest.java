package restAssuredBasic.POST;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class postAPIBDDTest {

    @Test
    public void postAPIBDD(){
        //create token

        //https://restful-booker.herokuapp.com/apidoc/index.html

        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType("application/json").body(payload).log().all()
                .when().log().all().post().then().log().all().statusCode(200);
    }
}
