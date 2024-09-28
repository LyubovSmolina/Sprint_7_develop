package ru.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.CONST;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class CreateAccountCourierSteps extends CONST {


    @Step("Запрос POST - создание учетной записи курьера")
    public static Response sendPostRequest_CreateCourierAccount(Courier random) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(random)
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }


    @Step("Запрос POST - создание второй индентичной учетной записи курьера")
    public static Response create_IdenticalCourierAccount(Courier courierData) {
        String json = "{\"login\": " + "\"" + courierData.getLogin() + "\"" + "," + " \"password\": " + "\"" + courierData.getPassword() + "\"," + " \"firstName\": " + "\"" + courierData.getFirstName() + "\"}";
        Response responseDouble = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(json)
                .when()
                .post(API_COURIER_CREATE);
        return responseDouble;
    }

    @Step("Создание аккаунта курьера с пустым полем \"Login\"")
    public static Response sendPostRequest_CreateAccountWithoutLogin() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutLogin())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"Password\"")
    public static Response sendPostRequest_CreateAccountWithoutPassword() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutPassword())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }

    @Step("Создание аккаунта курьера с пустым полем \"firstName\"")
    public static Response sendPostRequest_CreateAccountWithoutFirstName() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(Courier.withOutFirstName())
                .when()
                .post(API_COURIER_CREATE);
        return response;
    }

}