package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){
        //MyIpAddress:1000/ords/hr
        RestAssured.baseURI="http://184.73.36.228:1000/ords/hr";
    }
}
