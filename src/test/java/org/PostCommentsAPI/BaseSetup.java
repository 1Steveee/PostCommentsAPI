package org.PostCommentsAPI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;



public class BaseSetup {


    @BeforeClass
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Accept", "application/json")
                .build();

    }
}
