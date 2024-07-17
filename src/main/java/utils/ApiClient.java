package utils;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.api.models.CreateUserModel;
import utils.api.models.LoginUserModel;

import static io.restassured.RestAssured.given;

public class ApiClient {
    public static RequestSpecification defaultSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://stellarburgers.nomoreparties.site/");
        builder.addHeader("Content-Type", "application/json");
        return builder.build();
    }

    @Step("Send POST/api/auth/register")
    public static Response postCreateUser(CreateUserModel createUserModel) {
        return given()
                .spec(defaultSpecification())
                .body(createUserModel)
                .when()
                .post("/api/auth/register");
    }

    @Step("Send POST/api/auth/login")
    public static Response postLogInUser(LoginUserModel logInUserModel) {

        return given()
                .spec(defaultSpecification())
                .body(logInUserModel)
                .when()
                .post("/api/auth/login");
    }

    @Step("Send DELETE/api/auth/user")
    public static Response deleteUser(String accessToken) {
        RequestSpecification specification = defaultSpecification();

        specification.header("Authorization", accessToken);

        return given()
                .spec(specification)
                .delete("/api/auth/user");

    }

}
