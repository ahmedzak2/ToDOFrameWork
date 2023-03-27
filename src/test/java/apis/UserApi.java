package apis;

import base.Base;
import com.qcart.data.Route;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {
    static String baseUrl= "https://qacart-todo.herokuapp.com/";
static RestAssured restAssured =new RestAssured();

    public static Response   register(Users users){
        RestAssured.baseURI = "https://qacart-todo.herokuapp.com/";
        // to get all common requests  by use spec
      Response response = given().spec(Base.getRequestspecByuseRestAssuredclass())
              .body(users)
                .when().post(Route.registerPath)
              .then().log().all().extract().response();

        return response;
        /*
         * To extract the response then save it
         * */


    }

public static Response loginin(Users users){
   Response response= given().spec(Base.getRequestspec())
           .body(users)
            .when().post(Route.loginPath)
            .then().log().all().extract().response();

return  response;
}
}
