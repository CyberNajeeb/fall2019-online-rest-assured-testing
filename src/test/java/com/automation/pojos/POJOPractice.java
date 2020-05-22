package com.automation.pojos;

import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPractice {

    @BeforeAll
    public static void beforeAll() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }

    @Test
    public void getUser() {
        Response response = given().
                auth().basic("admin", "admin").
                when().
                get("/spartans/{id}", 478).prettyPeek();
        Spartan spartan = response.as(Spartan.class);
        System.out.println(spartan);
    }

    @Test
    public void addUser() {
        for (int i = 0; i < 9; i++) {

            Spartan spartan = new Spartan("Mohammed", "Male", 1234567890L);
            Gson gson = new Gson();
            String pojoAsJson = gson.toJson(spartan);
            System.out.println(pojoAsJson);

            Response response = given().auth().basic("admin", "admin").contentType(ContentType.JSON).body(spartan).
                    when().
                    post("/spartans").prettyPeek();
        }
    }
}