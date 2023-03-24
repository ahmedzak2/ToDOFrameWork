package models.steps;

import com.github.javafaker.Faker;
import models.ToDo;

public class TodoSteps {
    public static ToDo generateTOdo(){
        Faker faker =new Faker();

        String item = faker.book().title();
        boolean isCompleted = false;
           return new ToDo(isCompleted,item);

    }
}
