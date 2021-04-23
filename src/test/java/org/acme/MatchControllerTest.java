package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatchControllerTest {

    @Test
    void getMatches() {
        given()
                .when().get("/matches")
                .then()
                .statusCode(200);
    }

    @Test
    void getMatchById() {
        String uuid = "1038936";
        given()
                .pathParam("id", uuid)
                .when().get("/matches/{id}")
                .then()
                .statusCode(200);
    }
}