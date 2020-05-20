package com.automation.tests.day3;

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

public class ORDSTestsDay3 {

    @BeforeAll
    public static void setup() {
        baseURI = "http://54.224.118.38:1000/ords/hr";

//    Response response = given().get("/regions/{id}").prettyPeek();

    }

    @Test
    public void verifyFirstRegion() {
        given().
                pathParam("id", 1).
                when().
                get("/regions/{id}").prettyPeek().
                then().
                assertThat().statusCode(200).
                and().
                body("region_name", is("Europe")).
                and().
                body("region_id", is(1)).
                and().
                time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void verifyEmployee() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/employees");

        JsonPath jsonPath = response.jsonPath();
        String nameOfFirstEmployee = jsonPath.getString("items[0].first_name");
        System.out.println("name Of First Employee = " + nameOfFirstEmployee);

        String nameOfLastEmployee = jsonPath.getString("items[-1].first_name");
        System.out.println("name Of Last Employee = " + nameOfLastEmployee);

        Map<String, Objects> firstEmployee = jsonPath.get("items[0]");
        for (int i = 0; i < firstEmployee.size(); i++) {

            for (Object employees : firstEmployee.entrySet()) {
                System.out.println(employees);
            }
        }

    }
}
