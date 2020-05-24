package com.automation.pojos;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPracticeWithPreschool {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("Preschool.URI");
    }

    @Test
    public void addStudentTest(){
        File file = new File("student.json");
        Response response = given().
                contentType(ContentType.JSON).body(file).
                when().
                post("/student/create").prettyPeek();

        int studentID = response.jsonPath().getInt("studentId");
        ;
    }
}
