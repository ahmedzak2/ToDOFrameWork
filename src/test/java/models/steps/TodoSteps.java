package models.steps;

import apis.TodOApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import models.ToDo;

public class TodoSteps {
    public static ToDo generateTOdo(){
        Faker faker =new Faker();

        String item = faker.book().title();
        boolean isCompleted = false;
           return new ToDo(isCompleted,item);

    }
    public static String getTodoID(ToDo toDo,String token){
     Response response = TodOApi.addTodO(toDo,token);
    return  response.path("_id");
    }
}
