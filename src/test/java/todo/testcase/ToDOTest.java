package todo.testcase;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ToDOTest {
    String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MGY3ZjNkMmQ1YjEyMDAxNDY5ZDFjMCIsImZpcnN0TmFtZSI6ImFobWVkIiwibGFzdE5hbWUiOiJoYXplbSIsImlhdCI6MTY3ODczODM3MX0.xLw7qcgvf6hG39Flv4Cs4LkQ-63oZUjuF0SwIg7Ufro";

    String baseUrl= "https://qacart-todo.herokuapp.com/";

    @Test
    public void userShouldBeAbleToAddList(){
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","false");
        body.put("item","Learn Appium");
        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .body(body)
                .contentType(ContentType.JSON)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().body("isCompleted",equalTo(false))
                .assertThat().body("item",equalTo("Learn Appium"))

                .assertThat().statusCode(201);

    }
@Test
    public void userShouldBeGetAllAddList(){
        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().statusCode(200);

    }
    @Test
    public void userShouldBeGetOneAddList(){
        String taskId ="6412125549ced00014bf02a1";
        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks/"+taskId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("Learn Appium"),"isCompleted",equalTo(false));

}
    @Test
    public void userShouldBeuPADTEOneAddList(){
        String taskId ="6412125549ced00014bf02a1";
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","Learn Appium");

        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put ("/api/v1/tasks/"+taskId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("Learn Appium"),"isCompleted",equalTo(true));

    }
    @Test
    public void userShouldBeDeleteneAddList(){
        String taskId ="6412125549ced00014bf02a1";
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","Learn Appium");
        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().delete ("/api/v1/tasks/"+taskId)
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
