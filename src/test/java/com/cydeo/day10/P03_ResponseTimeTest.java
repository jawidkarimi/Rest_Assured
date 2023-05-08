package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.time(lessThan(1000L)) //this method is storing actual response time
                //.time(greaterThan(100L));
                .time(both(lessThan(1000L)).and(greaterThan(100L))).extract().response();

        System.out.println("response.getTime() = " + response.getTime());
        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));
    }
}
