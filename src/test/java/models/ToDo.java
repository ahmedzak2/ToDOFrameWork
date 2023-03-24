package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDo
{

    /*
    * Boolean is default value is null if I make it start with b small it will be with default is false
    * */
    @JsonProperty("isCompleted")

    private Boolean isCompleted ;

 private String item;
 /*
 * to show what equivalent in JSON when the word is strange shape
 * */
 @JsonProperty("_id")
 private String id;
 @JsonProperty("userID")
 private String userID;
 @JsonProperty("createdAt")
 private String createdAt;
  @JsonProperty("__v")
    private String v;


    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("_id")

    public String getId() {
        return id;
    }
    @JsonProperty("_id")

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ToDo(String item) {
        this.item = item;
    }

    @JsonProperty("__v")

    public String getV() {
        return v;
    }

    @JsonProperty("__v")
    public void setV(String v) {
        this.v = v;
    }

    public ToDo(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }

    public ToDo() {

    }
}
