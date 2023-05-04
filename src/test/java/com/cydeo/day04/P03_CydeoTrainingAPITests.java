package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {

    /*
    Given accept type is application/json
    And path param request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server is envoy
    And verify following
            firstName Mark
            batch 13
            major math
            emailAddress mark@email.com
            companyName Cydeo
            street 777 5th Ave
            zipCode 33222
 */
    @DisplayName("GET /student/2")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //And Date header is exist
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

        //And Server header is envoy
        assertEquals("envoy",response.header("server"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals("Mark",jsonPath.getString("students[0].firstName"));
        assertEquals(13,jsonPath.getInt("students[0].batch"));
        assertEquals("math",jsonPath.getString("students[0].major"));
        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));
        assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));
        assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));
    }

    /*
    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch{batch}
    Then status code is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */
}
