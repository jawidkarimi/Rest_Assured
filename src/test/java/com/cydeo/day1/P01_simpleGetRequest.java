package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url = "http://18.207.211.65:8000/api/spartans";

    /*
    When user send request to /api/spartans endpoint
    Then user should be able to see status code is 200
    and Print out response body into screen
     */

    @Test
    public void simpleRequest(){
        //send request to url and get response as Response interface
       Response response = RestAssured.get(url);
        System.out.println("response.statusCode() = " + response.statusCode());

        //verify that status code is 200
        int actualStatusCode = response.statusCode();

        //assert that it is 200
        Assertions.assertEquals(200,actualStatusCode);

        //how to print json response body on console
        response.prettyPrint();
    }
}
