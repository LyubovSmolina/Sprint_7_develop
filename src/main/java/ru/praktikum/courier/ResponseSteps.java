package ru.praktikum.courier;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikum.CONST.*;

public class ResponseSteps {
    @Step("Код и статус ответа 201 Created. Тело ответа: \"ok:\" \"true\"")
    public static void statusCodeAndBodyCreatedCourierAccount(Response response) {
        response.then().statusCode(CREATED201)
                .and().assertThat().body("ok",  equalTo(true));
    }
    @Step("Код и статус ответа 409 Conflict. Тело ответа: \"Этот логин уже используется\"")
    public static void statusCodeAndBodyConflictIdenticalCourierAccount(Response responseDouble) {
        responseDouble.then().statusCode(CONFLICT409)
                .and().assertThat().body("message",  equalTo("Этот логин уже используется"));
    }
    @Step("Код и статус ответа 200 Ok. Тело ответа: номер \"id\"  курьера в системе")
    public static void statusCodeAndBodyLogInCourierAccount(Response response) {
        response.then().statusCode(OK200)
                .and().assertThat().body("id", notNullValue());
    }
    @Step("Код и статус ответа 404 Not Found при попытке входа в аккаунт с невалидным логином/паролем. Тело ответа: \"Учетная запись не найдена\"")
    public static void statusCodeAndBodyLogInCourierAccountWithInvalidData(Response response) {
        response.then().statusCode(NOT_FOUND404)
                .and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
    @Step("Код и статус ответа 400 BR, при попытке создания аккаунта курьера с пустым полем \"Login\"/\"password\"")
    public static void statusCodeAndBodyBadRequestCreateAccountWithoutData(Response response) {
        response.then().statusCode(BAD_REQUEST400)
                .and().assertThat().body("message",  equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Step("Код и статус ответа 400 BR, при попытке авторизации курьера с пустым полем \"Login\"/\"password\"")
    public static void statusCodeAndBodyLogInCourierAccountWithOutData(Response response) {
        response.then().statusCode(BAD_REQUEST400)
                .and().assertThat().body("message",  equalTo("Недостаточно данных для входа"));
    }
}
