package models.steps;

import apis.UserApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.Users;

public class UserSteps {
    public  static  Users generateUser(){
        /*
        * we use the Faker dependency to generate  fake items as above
        * */
        Faker faker =new Faker();
         String  firstName = faker.name().firstName() ;
         String lastName= faker.name().lastName();
         String email = faker.internet().emailAddress();
         String password = faker.internet().password();
        return new Users(firstName,lastName,email,password);
    }
/*
* To create fake user then register it
* */

    public static Users getRegisterdUser(){
        Users users = generateUser();
        UserApi.register(users);
    return users;
    }

    /*
    * to generate token and access  make TodoList
    * */
    public static String getUserToken(){
        Users users = generateUser();

       Response response =  UserApi.register(users);
return response.path("access_token");

    }
}
