package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsonPath extends SpartanTestBase {


    /*
    Given accept type is json
    And path param id is 10
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And response payload values match the following:
    id is 10,
    name is "Lorenza",
    gender is "Female"
    phone is 3312820936
     */

    @DisplayName("Get spartan with Response Path")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
        response.prettyPeek();

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());


        //And response payload values match the following:
        //id is 10,
        //name is "Lorenza",
        //gender is "Female"
        //phone is 3312820936

        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());

        //we saved our response as JSONPATH object
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        //assertion
        assertEquals("Lorenza",name);
        assertEquals(10,id);

    }
}
