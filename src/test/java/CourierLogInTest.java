import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.courier.Courier;

import static io.restassured.RestAssured.baseURI;
import static ru.praktikum.courier.CreateAccountCourierSteps.sendPostRequest_CreateCourierAccount;
import static ru.praktikum.courier.LogInCourierSteps.*;
import static ru.praktikum.courier.ResponseSteps.*;

public class CourierLogInTest {


    @Before
    public void setUp() {
        baseURI = "https://qa-scooter.praktikum-services.ru/";
    }


    @Test
    @DisplayName("Успешная авторизация курьера в системе")
    public void Success_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response2 = logIn_CourierAccount(courier);
        statusCodeAndBody_LogInCourierAccount(response2);

        System.out.println(response2.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации под несуществующим пользователем")
    public void invalidAccount_LoginCourierTest() {
        Response response = invalidCourierAccount();
        statusCodeAndBodyLogInCourierAccount_WithInvalidData(response);

        System.out.println(response.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации c невалидным паролем")
    public void invalidPassword_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_InvalidPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithInvalidData(response1);

        System.out.println(response1.body().asString());
    }

    @Test
    @DisplayName("Ошибка авторизации c невалидным логином")
    public void invalidLogin_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_InvalidLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithInvalidData(response1);

        System.out.println(response1.body().asString());

    }
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"login\"")
    public void nullLogin_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_NullLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithOutData(response1);

        System.out.println(response1.body().asString());

    }

    //При запросе со значением null в поле "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"password\"")
    public void nullPassword_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_NullPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithOutData(response1);

        System.out.println(response1.body().asString());

    }

    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"login\"")
    public void bodyWithOutLogin_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_WithOutLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithOutData(response1);

        System.out.println(response1.body().asString());

    }

    //При запросе без поля "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"password\"")
    public void bodyWithOutPassword_LoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response response1 = logIn_WithOutPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount_WithOutData(response1);

        System.out.println(response1.body().asString());

    }



}


