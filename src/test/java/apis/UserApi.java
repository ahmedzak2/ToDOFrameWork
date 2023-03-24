package apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {
    static String baseUrl= "https://qacart-todo.herokuapp.com/";

    public static Response   register(Users users){
      Response response = given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(users)
                .when().post("/api/v1/users/register")
              .then().log().all().extract().response();

        return response;
        /*
         * To extract the response then save it
         * */


    }

public static Response loginin(Users users){
   Response response= given().baseUri(baseUrl)
            .log().all()
            .contentType(ContentType.JSON)
            .body(users)
            .when().post("/api/v1/users/login")
            .then().log().all().extract().response();

return  response;
}
}
