package core;

import core.model.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public abstract class RequestHandler {
    public static final String getAllEmplPath = "/employees";
    public static final String getEmplPath = "/employee/";
    public static final String createPath = "/create";
    public static final String updatePath = "/update/";
    public static final String deletePath = "/delete/";

    public static Response getEmployeeRequest(User user){
        return RestAssured.get(getEmplPath + user.getId());
    }

    public static Response getEmployeesRequest(){
        return RestAssured.get(getAllEmplPath);
    }

    public static Response createEmployeeRequest(User user){
        return RestAssured.given().contentType(ContentType.JSON).body(user.toJsonString()).post(createPath);
    }

    public static Response updateEmployeeRequest(User user){
        return RestAssured.given().contentType(ContentType.JSON).body(user.toJsonString()).put(updatePath + user.getId());
    }

    public static Response deleteEmployeeRequest(User user){
        return  RestAssured.delete(deletePath + user.getId());
    }

    public static Response deleteEmployeeRequest(String id){
        return  RestAssured.delete(deletePath + id);
    }

}
