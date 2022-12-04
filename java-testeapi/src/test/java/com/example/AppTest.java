package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Import dependencies.
 */
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.containsString; 


public class AppTest 
{

    private String url = "https://jsonplaceholder.typicode.com/users/";
    /**
     * Requisitions Test
     */
    @Test
    public void getTest()
    {
        when()
        .get(url)
        .then()
        .statusCode(200);

    }
}
