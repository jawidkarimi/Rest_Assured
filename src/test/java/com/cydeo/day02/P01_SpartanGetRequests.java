package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.RequestingUserName;

public class P01_SpartanGetRequests {


    String url = "http://18.207.211.65:8000";

    /*
     *Given content type is application/json
     * When user sends Get request /api/spartans endpoint
     * Then status code should be 200
     * And content type should be application/json
     */

    @Test
    public void getAllSpartans() {

        Response response = RestAssured.given()
                                 .accept(ContentType.JSON) //hey api please send me json response
                             .when()
                                .get(url + "/api/spartans");

        //print the response body
        //response.prettyPrint();

        //how to get status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);

        //how to get response content type header?
        String actualContentType = response.contentType();
        System.out.println(actualContentType);

        //assert the content type
        Assertions.assertEquals("application/json",actualContentType);

        //how to get Connection header value?
        //if we want to get any response header value, we can use header("headerName")
        //method from response object. it will return header value as string

        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));

        //how to verify header exist?
        //hasHeaderWithName method help us to verify header exists or not
        //it is useful for dynamic header values like Date, we are only verifying header exist or not, not checking the value
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);
    }


    @Test
    public void getSpartan(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when().get(url + "/api/spartans/3");

        //verify status code
        Assertions.assertEquals(200,response.statusCode());

        //verify contentType is json
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals("application/json",response.getContentType());
        Assertions.assertEquals("application/json",response.header("Content-type"));
        Assertions.assertEquals(ContentType.JSON.toString(),response.header("Content-type"));

        response.prettyPrint();

        //verify body contains "Fidole"
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
        /*
        This is not a good way to make assertion. In this way we are just converting response to String and with the help of String cont
        contains we are just looking intoREsponse. But we should be able to get json "name" key value then verify that one is equal to "Fidole"
          */
    }


    /*
    Given no headers provided
    When Users send GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain Date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

    @Test
    public void helloSpartan(){
        Response response = RestAssured.when().get(url+"/api/hello");

        //print result on the console
        response.prettyPrint();

        //verify the status code
        Assertions.assertEquals(200,response.statusCode());

        //And Content type header should be "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //And header should contain Date
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

        //And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        //And body should be "Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));

    }
}
