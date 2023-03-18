package todo.testcase;

import io.restassured.http.ContentType;
import models.Users;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest {
    String baseUrl= "https://qacart-todo.herokuapp.com/";

@Test
public void  ClientShouldBeAbleRegister(){
    HashMap <String,String>body = new HashMap<>();
body.put("firstName","ahmed");
body.put("lastName","hazem");
body.put("email","ahmedziko5000@gmail.com");
body.put("password","123456");
    given().baseUri(baseUrl)
            .log().all()
            .contentType(ContentType.JSON)
            .body(body)
            .when().post("/api/v1/users/register")
            .then().log().all()
            .assertThat().statusCode(201)
            .assertThat().body("firstName",equalTo("ahmed"));

}
@Test
    public void customerRegisterWithexistingAccount(){
   /* HashMap <String,String>body = new HashMap<>();*/

   /* body.put("firstName","ahmed");*/
   /* body.put("lastName","hazem");*/
   /* body.put("email","ahmedziko51000@gmail.com");*/
   /* body.put("password","123456");*/
    Users users=new Users();
    users.setFirstName("ahmed");
    users.setLastName("hazem");
    users.setEmail("Admin121@test.com");
    users.setPassword("123456");
    given().baseUri(baseUrl)
            .log().all()
            .contentType(ContentType.JSON)
            .body(users)
            .when().post("/api/v1/users/register")
            .then().log().all()
            .assertThat().statusCode(200)
            .assertThat().body("message", equalTo("Email is already exists in the Database"));


}
    @Test
    public void customerRegisterByUseConstruct(){
      /*
      * To send all variable by use constrictor and make sure the arrangement is success
      * */
        Users users=new Users("ahmed","hazem","zik30@gmail.com","12345");
        given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(users)
                .when().post("/api/v1/users/register")
                .then().log().all()
                .assertThat().statusCode(2001)
                .assertThat().body("message", equalTo("Email is already exists in the Database"));


    }
@Test
    public void  clientLoginWithExistingAccount(){
        HashMap <String,String>body = new HashMap<>();
        body.put("email","ahmedziko5000@gmail.com");
        body.put("password","123456");
        given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", equalTo("ahmed"))
                .assertThat().body("access_token",not( empty()));


    }
    @Test
    public void  clientLoginWithwrongInput(){
        HashMap <String,String>body = new HashMap<>();
        body.put("email","ahmedziko5000@gmail.com");
        body.put("password","12456");
        given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("The email and password combination is not correct, please fill a correct email and password"));


    }
    @Test
    public void  clientLoginbByConstructor(){
Users  users =new Users("ahmedziko500@gmail.com","123456");
        given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(users)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("message",equalTo("The email and password combination is not correct, please fill a correct email and password"));


    }
}
