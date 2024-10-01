package ru.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.CONST;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class CreateAccountCourierSteps extends CONST {


    @Step("Запрос POST - создание учетной записи курьера")
    public static Response sendPostRequestCreateCourierAccount(Courier random) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(random)
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }


    @Step("Запрос POST - создание второй индентичной учетной записи курьера")
    public static Response createIdenticalCourierAccount(Courier courier) {
        Response responseDouble = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Credentials.from(courier))
                .when()
                .post(API_COURIER_CREATE);
        return responseDouble;
    }

    @Step("Создание аккаунта курьера с пустым полем \"Login\"")
    public static Response sendPostRequestCreateAccountWithoutLogin() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutLogin())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"Password\"")
    public static Response sendPostRequestCreateAccountWithoutPassword() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutPassword())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"firstName\"")
    public static Response sendPostRequestCreateAccountWithoutFirstName() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutFirstName())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }


}