package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;

public class ORDSTests {
    String BASE_URL = "http://3.90.112.152:1000/ords/hr";

    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees() {
        //response can be saved in the Response object
        //prettyPeek() - method that prints response info in nice format
        //also ths method returns Response object
        //response contains body, header and status line
        //body (payload) - contains content that we requested from the web service
        //header - contains meta data
        Response response = given().baseUri(BASE_URL).when().get("/employees").prettyPeek();

        /**
         * RestAssured request has similar structure to BDD scenarios:
         * Start building the request part of the test
         *
         * given() - used for request setup and authentication
         * Syntactic sugar,
         * when() - to specify type of HTTP request: get, put, post, delete, patch, head, etc...
         * then() - to verify response, perform assertions
         */
    }

    @Test
    @DisplayName("Get one employee with ID")
    public void getOneEmployee(){
        //in URL we can specify path and query parameters
        //path parameters are used to retrieve specific resource: for example 1 employee not all of them
        //{id} - path variable, that will be replace with a value after comma
        //after when() we specify HTTP request type/method/verb
        //The path parameters. E.g. if path is "/book/{hotelId}/{roomNumber}" you can do <code>get("/book/{hotelName}/{roomNumber}", "Hotels R Us", 22);</code>.
        Response response = given().baseUri(BASE_URL).when().get("/employees/{id}", 100).prettyPeek();
        //how we verify response? - use assertions
        response.then().statusCode(200);
        int statusCode = response.getStatusCode();//to save status code in a variable
        Assertions.assertEquals(200, statusCode);
        //Or
        Assertions.assertTrue(response.statusLine().endsWith("OK"));

        /**
         * given base URI = http://3.90.112.152:1000/ords/hr
         * when user sends get request to "/countries"
         * then user verifies that status code is 200
         */
    }

    @Test
    @DisplayName("Get list of countries/verify 200 status code")
    public void getCountries(){
        given().baseUri(BASE_URL).when().get("/countries").prettyPeek().then().statusCode(200);
        //how we verify response? - use assertions
    }

    @Test
    @DisplayName("Get list of countries by ID/verify 200 status code")
    public void getCountriesByID(){
        given().baseUri(BASE_URL).when().get("/countries/{id}", "AU").prettyPeek().then().statusCode(200);
        //how we verify response? - use assertions
    }
}