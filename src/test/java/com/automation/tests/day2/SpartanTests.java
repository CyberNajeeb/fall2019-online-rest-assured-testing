package com.automation.tests.day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanTests {
    String BASE_URL = "http://54.224.118.38:8000";

    @Test
    @DisplayName("Get list of all spartans")
    public void getCountriesByID() {
        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).when().get("/api/spartans").
                prettyPeek().then().statusCode(200);
//        how we verify response? - use assertions
    }

    @Test
    @DisplayName("Add new Spartan")
    public void addNewSpartan() {
        String body = "{\"gender\": \"Male\", \"name\": \"Mohammed\",\"phone\": 3218792353}";
        File jsonFile = new File(System.getProperty("user.dir") + "/Spartan.json");

        given().
                contentType(ContentType.JSON).
                auth().basic("admin", "admin").
                body(jsonFile).
                baseUri(BASE_URL).when().post("/api/spartans").prettyPeek().then().statusCode(201);
    }

    @Test
    @DisplayName("Delete some spartan and verify that status code is 204")
    public void deleteSpartanTest() {
        //{id} - path parameter
        //YOU CANNOT DELETE SOMETHING TWICE
        //we yse delete() method to delete something
        //204 - No content, most common status code for successful delete action
        //authentication - who you are? you need to tell to the server who you are before getting any data
        //ALL HTTP STATUS CODES HAVE SAME MEANING EVERYWHERE
        // 201 - always after successful POST request
        // 200 - always after successful GET request
        // 204 - always after successful DELETE request
        // 4XX - always after unsuccessful request and it was YOUR FAULT
        //

        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).
                when().
                delete("/api/spartans/{id}", 165).prettyPeek().
                then().
                statusCode(204);

    }

}