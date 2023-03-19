package todo.testcase;

import apis.UserApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Users;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {
@Test
public void  ClientShouldBeAbleRegisterwith(){
   /* HashMap <String,String>body = new HashMap<>();
body.put("firstName","ahmed");
body.put("lastName","hazem");
body.put("email","ahmedziko5000@gmail.com");
body.put("password","123456");
   */
    Users users=new Users("ahmed","hazem","ahmedziko51000@gmail.com","123456");
    Response response= UserApi.register(users);
    /*
     * to make Rest assured to make dezrilation for response body then save
     *  it in user class
     *
     * to save the needed output for response to save it in user class
     * */

    Users basicInformation = response.body().as(Users.class);
    assertThat(basicInformation.getFirstName(),equalTo(users.getFirstName()));

    assertThat( response.statusCode(),equalTo(201));
    assertThat(response.path("firstName"),equalTo("ahmed"));

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
    users.setEmail("Admin12313333@test.com");
    users.setPassword("123456");
 Response response=   UserApi.register(users);


    Error error = response.body().as(Error.class);

    /*
    * To separate  the assert form the function by save the response to make it varibale
    * */
assertThat(error.getMessage(),equalTo("Email is already exists in the Database"));
assertThat(response.statusCode(),equalTo(400));

}
    @Test
    public void customerRegisterByUseConstruct(){
      /*
      * To send all variable by use constrictor and make sure the arrangement is success
      * */
        Users users=new Users("ahmed","hazem","zik303334444@gmail.com","12345");
       Response response= UserApi.register(users);

        /*
         * to make Rest assured to make dezrilation for response body then save
         *  it in user class
         *
         * to save the needed output for response to save it in user class
         * */


            /*    .assertThat().statusCode(2001)
                .assertThat().body("message", equalTo("Email is already exists in the Database"));
*/
        Users basicInformation = response.body().as(Users.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(basicInformation.getFirstName(),equalTo(users.getFirstName()));

    }
@Test
    public void  clientLoginWithExistingAccount(){
      /*  HashMap <String,String>body = new HashMap<>();
        body.put("email","ahmedziko5000@gmail.com");
        body.put("password","123456");
      */
    Users users = new Users("zik303334444@gmail.com","12345");
Response response=      UserApi.loginin(users);
    Users basicInformation = response.body().as(Users.class);

assertThat(response.statusCode(),equalTo(200));
assertThat(response.path("firstName"),equalTo("ahmed"));
    assertThat (basicInformation.getAccessToken(),notNullValue());


/* given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", equalTo("ahmed"))
                .assertThat().body("access_token",not( empty()));

*/
    }
    @Test
    public void  clientLoginWithwrongInput(){
    /*    HashMap <String,String>body = new HashMap<>();
        body.put("email","ahmedziko5000@gmail.com");
        body.put("password","12456");
        given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("The email and password combination is not correct, please fill a correct email and password"));    */
        Users users = new Users("zik303334444@gmail.com","1234567");
        Response response=      UserApi.loginin(users);
        Error error = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(401));
        assertThat(error.getMessage(),equalTo("The email and password combination is not correct, please fill a correct email and password"));


    }
    @Test
    public void  clientLoginbByConstructor(){
Users  users =new Users("ahmedziko500@gmail.com","123456");
     /*   given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body(users)
                .when().post("/api/v1/users/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("message",equalTo("The email and password combination is not correct, please fill a correct email and password"));

*/
    }
}
