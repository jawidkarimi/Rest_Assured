package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){
        //MyIpAddress:1000/ords/hr
        RestAssured.baseURI="http://18.207.211.65:1000/ords/hr";
    }
}
