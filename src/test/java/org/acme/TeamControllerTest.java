package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TeamControllerTest {

    @Test
    void getTeamById() {
        String uuid = "133768";
        given()
                .pathParam("id", uuid)
                .when().get("/teams/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void getTeams() {
        given()
                .when().get("/teams")
                .then()
                .statusCode(200);
    }
}