package base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Base {
  //  static String baseUrl= "https://qacart-todo.herokuapp.com/";
  static String baseUrl= getEnv();
/*
*
* to make the common command is i=one file to use
* */

    public static RequestSpecification getRequestspec(){
        RequestSpecification requestSpecification =given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON);
   return requestSpecification;
    }
public static String getEnv(){
   String  env= System.getProperty("env","Production");
   String baseUrl;
switch (env){
    case "Production":
        baseUrl="https://qacart-todo.herokuapp.com/";
break;
    case "testing":
        baseUrl=" http://localhost:3000/";
        break;
    default:
        throw new RuntimeException("Enviroment is not supported");
}
return baseUrl;
}
}
