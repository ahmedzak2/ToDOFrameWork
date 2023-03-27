package todo.testcase;

import apis.UserApi;
import com.qcart.data.ErrorData;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Users;
import models.steps.UserSteps;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
* As cuccmber  to make it features
* */
@Feature("user feature")
public class UserTest {

    @Story("user register ")
@Test(description = " ClientShouldBeAbleRegisterwith")
public void  ClientShouldBeAbleRegisterwith(){
   /* HashMap <String,String>body = new HashMap<>();
body.put("firstName","ahmed");
body.put("lastName","hazem");
body.put("email","ahmedziko5000@gmail.com");
body.put("password","123456");
   */
    Users users= UserSteps.generateUser();
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
    assertThat(response.path("firstName"),equalTo(users.getFirstName()));

}

/*
* As scerniao  in cucumber
* */
@Story(" user register with fake data")
@Test(description = "customerRegisterWithexistingAccount by using fake")
    public void customerRegisterWithexistingAccount(){
   /* HashMap <String,String>body = new HashMap<>();*/

   /* body.put("firstName","ahmed");*/
   /* body.put("lastName","hazem");*/
   /* body.put("email","ahmedziko51000@gmail.com");*/
   /* body.put("password","123456");*/
   /* users.setFirstName("ahmed");
    users.setLastName("hazem");
    users.setEmail("Admin12313333@test.com");
    users.setPassword("123456");
*/
    Users users=UserSteps.getRegisterdUser();

    Response response=   UserApi.register(users);


    Error error = response.body().as(Error.class);

    /*
    * To separate  the assert form the function by save the response to make it varibale
    * */
assertThat(error.getMessage(),equalTo(ErrorData.userAlredyRegister));
assertThat(response.statusCode(),equalTo(400));

}
    @Test
    public void customerRegisterByUseConstruct(){
      /*
      * To send all variable by use constrictor and make sure the arrangement is success
      * */
        Users users=new Users("ahmed","hazem","zik3033344222121323144@gmail.com","12345");
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
    @Story("user login successfully ")
@Test
    public void  clientLoginWithExistingAccount(){
      /*  HashMap <String,String>body = new HashMap<>();
        body.put("email","ahmedziko5000@gmail.com");
        body.put("password","123456");
      */
   // Users users = new Users("zik303334444@gmail.com","12345");
    Users users=UserSteps.getRegisterdUser();
Users loginData = new Users(users.getEmail(),users.getPassword());
    Response response=      UserApi.loginin(loginData);
    Users basicInformation = response.body().as(Users.class);

assertThat(response.statusCode(),equalTo(200));
assertThat(response.path("firstName"),equalTo(users.getFirstName()));
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
        Users users=UserSteps.getRegisterdUser();
        Users loginData = new Users(users.getEmail(),"saad");

     Response response=      UserApi.loginin(loginData);

        Error error = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(401));
        assertThat(error.getMessage(),equalTo(ErrorData.wrongInupt));


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
