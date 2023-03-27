package apis;

import base.Base;
import com.qcart.data.Route;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.ToDo;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class TodOApi {
   static String baseUrl= "https://qacart-todo.herokuapp.com/";
    public static Response addTodO( ToDo list,String token){
        RestAssured.baseURI = Base.getEnv();

        Response response= given().spec(Base.getRequestspecByuseRestAssuredclass())
                .auth().oauth2(token)
                .body(list)
                .when().post(Route.TODOSRoute)
                .then().log().all().extract().response();
return  response;
    }

    public static Response getAllTodoLIst(String token){
       Response response= given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get(Route.TODOSRoute)
                .then().log().all().extract().response();
        return response;
    }
    public static Response getTodoItem(String taskId,String token){
        Response response= given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().get(Route.TODOSRoute+taskId)
                .then().log().all().extract().response();
        return response;
    }
    public static  Response deleteTodoList(String taskId,String token){
        Response response =  given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when().delete (Route.TODOSRoute+taskId)
                .then().log().all().extract().response();
return response;
    }
    public static Response updateTodoList(ToDo body,String taskId,String token){
        Response response=given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put (Route.TODOSRoute+taskId)
                .then().log().all().extract().response();
    return  response;
    }


}
