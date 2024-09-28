package ru.praktikum.courier;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static ru.praktikum.CONST.API_COURIER_LOGIN;

public class LogInCourierSteps {
    @Step("Вход в существующий аккаунт курьера")
    public static Response logIn_CourierAccount(Courier courier) {
        Gson gson = new Gson();
        String json = gson.toJson(courier);
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация несуществующего аккаунта")
    public static Response invalidCourierAccount() {
        String json = "{\"login\": \"netShustrik\", \"password\": \"555555\"}";
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response;
    }

    @Step("Авторизация курьера с невалидным паролем")
    public static Response logIn_InvalidPasswordCourierAccount(Courier courier) {

        String json = "{\"login\": " + "\"" + courier.getLogin() + "\"" + "," + "\"password\": \"741852\"}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера с невалидным логином")
    public static Response logIn_InvalidLoginCourierAccount(Courier courier) {
        String json = "{\"login\": \"netShustrika\"," + "\"password\": " + "\"" + courier.getPassword() + "\"}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера со значением null в поле \"Login\"")
    public static Response logIn_NullLoginCourierAccount(Courier courier) {
        String json = "{\"login\": " + null + "," + "\"password\": " + "\"" + courier.getPassword() + "\"}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }
    @Step("Авторизация курьера со значением null в поле \"Password\"")
    public static Response logIn_NullPasswordCourierAccount(Courier courier) {
        String json = "{\"login\": " + "\"" + courier.getLogin() + "\"," + "\"password\": " + null + "}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера при отсутствии в теле запроса пары ключ-значение \"Login\"")
    public static Response logIn_WithOutLoginCourierAccount(Courier courier) {
        String json = "{\"password\": " + "\"" + courier.getPassword() + "\"}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }

    @Step("Авторизация курьера при отсутствии в теле запроса пары ключ-значение \"Password\"")
    public static Response logIn_WithOutPasswordCourierAccount(Courier courier) {
        String json = "{\"login\": " + "\"" + courier.getLogin() + "\"}";
        Response response1 = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_LOGIN);
        return response1;
    }
}
