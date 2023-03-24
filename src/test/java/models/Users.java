package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
* to make it sure exculde any variable which don't have value ( the value set after success)
* */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
   private String  firstName;
   private String lastName;
   private String email;
   private String password;

   /*
   * to make the  app know where serach for this value
   * to make it take it form response and must determine which method to take it form hi
   *
   * */
   @JsonProperty("access_token")
   private String accessToken;
   private String userID;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public Users() {

         }

    /*
    * i can make it receive all value with constructor
    * */
    public Users(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
