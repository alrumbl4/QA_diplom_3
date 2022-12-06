package site.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.stellarburgers.Credentials;
import site.stellarburgers.User;

import static io.restassured.RestAssured.given;

public class UserClient extends Client{

    protected static final String USER_REGISTRATION_PATH = "api/auth/register";
    protected static final String USER_AUTHORIZATION_PATH = "api/auth/login";
    protected static final String USER_DELET_AND_EDIT_PATH = "api/auth/user";

    @Step("Регистрация нового пользователя")
    public ValidatableResponse registrationUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(USER_REGISTRATION_PATH)
                .then();
    }

    @Step("Авторизивация пользователя")
    public ValidatableResponse authorizationUser(Credentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(USER_AUTHORIZATION_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public  ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .delete(USER_DELET_AND_EDIT_PATH)
                .then();
    }
}
