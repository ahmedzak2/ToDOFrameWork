package apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.ToDo;

import static io.restassured.RestAssured.given;

public class TodOApi {
   static String baseUrl= "https://qacart-todo.herokuapp.com/";

    public static Response addTodO( ToDo list,String token){
        Response response= given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .body(list)
                .contentType(ContentType.JSON)
                .when().post("/api/v1/tasks")
                .then().log().all().extract().response();
return  response;
    }

    public static Response getAllTodoLIst(String token){
       Response response= given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks")
                .then().log().all().extract().response();
        return response;
    }
    public static Response getTodoItem(String taskId,String token){
        Response response= given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get("/api/v1/tasks"+taskId)
                .then().log().all().extract().response();
        return response;
    }
    public static  Response deleteTodoList(String taskId,String token){
        Response response =  given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().delete ("/api/v1/tasks/"+taskId)
                .then().log().all().extract().response();
return response;
    }
    public static Response updateTodoList(ToDo body,String taskId,String token){
        Response response=given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put ("/api/v1/tasks/"+taskId)
                .then().log().all().extract().response();
    return  response;
    }


}
