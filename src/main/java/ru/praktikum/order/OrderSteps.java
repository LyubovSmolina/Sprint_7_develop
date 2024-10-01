package ru.praktikum.order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikum.CONST.API_ORDERS;
import static ru.praktikum.CONST.CREATED201;

public class OrderSteps extends OrdersData {

    @Step("Запрос POST - создание заказа на аренду самоката со значением ключа \"color\": \"black\"/\"grey\"; \"black\" and \"grey\"; без выбора цвета")
    public static Response sendPostRequestOrderSelectScooterColor(OrdersData colorSelect) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(baseURI)
                .body(colorSelect)
                .when()
                .post(API_ORDERS);
        return response;
    }

    @Step("Код и статус ответа 201 Created. Тело ответа содержит значение ключа \"track\"")
    public static void statusCodeAndBodyOrderSelectScooterColor(Response response) {
        response.then().statusCode(CREATED201)
                .and().assertThat().body("track", notNullValue());
    }

}
