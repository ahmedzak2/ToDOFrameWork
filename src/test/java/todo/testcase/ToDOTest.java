package todo.testcase;

import apis.TodOApi;
import com.qcart.data.ErrorData;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.ToDo;
import models.Users;
import models.steps.TodoSteps;
import models.steps.UserSteps;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Feature("Todo")
public class ToDOTest {
    String token = UserSteps.getUserToken();
    String baseUrl= "https://qacart-todo.herokuapp.com/";

    @Story("user add new list")
    @Test
    public void userShouldBeAbleToAddList(){
/*
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","false");
        body.put("item","Learn Appium");
*/

      ToDo body = TodoSteps.generateTOdo();
       Response response= TodOApi.addTodO(body,token);
                assertThat(response.path("isCompleted"),equalTo(false));
                assertThat(response.path("item"),equalTo(body.getItem()));
                assertThat(response.statusCode(),equalTo(201));
ToDo reternToDo = response.body().as(ToDo.class);
        assertThat(reternToDo.getIsCompleted(),equalTo(false));
        assertThat(reternToDo.getItem(),equalTo(body.getItem()));

    }

  /*  @Test
    public void userShouldBeAbleToAddListByConstructor(){

        ToDo list =new ToDo(false,"Learn Appium");
        given().baseUri(baseUrl)
                .log().all()
                .auth().oauth2(token)
                .body(list)
                .contentType(ContentType.JSON)
                .when().post("/api/v1/tasks")
                .then().log().all()
                .assertThat().body("isCompleted",equalTo(false))
                .assertThat().body("item",equalTo("Learn Appium"))

                .assertThat().statusCode(201);

    }*/
    @Test
    public void userShouldBeNotAbleToAddListByConstructorIfIsCompletedIsMissing(){

        ToDo list =new ToDo("Learn Appium");
     Response response =  TodOApi.addTodO(list,token);

     Error error= response.body().as(Error.class);
                assertThat(error.getMessage(),equalTo(ErrorData.isCOmpletedMissing));
                assertThat(response.statusCode(),equalTo(400));

    }
@Test
    public void userShouldBeGetAllAddList(){
        Response response=TodOApi.getAllTodoLIst(token);

    assertThat(response.statusCode(),equalTo(200));

    }
    @Test

    /*
    * Why there error in here  shown as
    *
    * java.lang.IllegalStateException: Cannot parse object because no supported Content-Type was specified in response. Content-Type was 'text/html; charset=UTF-8'.
     * */
    public void userShouldBeGetOneAddList(){
        ToDo toDo = TodoSteps.generateTOdo();

        String taskId =TodoSteps.getTodoID(toDo,token);
        Response response = TodOApi.getTodoItem(taskId,token);
        ToDo reternToDo = response.body().as(ToDo.class);
        assertThat(response.statusCode(),equalTo(200));
         assertThat(reternToDo.getItem(),equalTo(toDo.getItem()));

        // assertThat(reternToDo.getItem(),equalTo("Learn Appium"));
       // assertThat(reternToDo.getIsCompleted(),equalTo(false));

}
    @Test
    public void userShouldBeuPADTEOneAddList(){
        ToDo toDo = TodoSteps.generateTOdo();

        String taskId =TodoSteps.getTodoID(toDo,token);
      /*  HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","Learn Appium");*/
        Response response = TodOApi.updateTodoList(toDo,taskId,token);
        ToDo reternToDo = response.body().as(ToDo.class);

        assertThat(response.statusCode(),equalTo(200));
                assertThat(reternToDo.getItem(),equalTo(toDo.getItem()));
                assertThat(reternToDo.getIsCompleted(),equalTo(false));
    }
    @Test
    public void userShouldBeDeleteneAddList(){
        ToDo toDo = TodoSteps.generateTOdo();

        String taskId =TodoSteps.getTodoID(toDo,token);

/*
        String taskId ="64176b4f44a62700145487aa";
        HashMap<String,String> body = new HashMap<>();
        body.put("isCompleted","true");
        body.put("item","Learn Appium");
*/
       Response response = TodOApi.deleteTodoList(taskId,token);
       assertThat(response.statusCode(),equalTo(404));

    }
}
