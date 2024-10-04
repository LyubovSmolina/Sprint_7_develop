import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.courier.Courier;
import ru.praktikum.courier.Credentials;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static ru.praktikum.CONST.*;
import static ru.praktikum.courier.CreateAccountCourierSteps.sendPostRequestCreateCourierAccount;
import static ru.praktikum.courier.LogInCourierSteps.*;
import static ru.praktikum.courier.ResponseSteps.*;

public class CourierLogInTest extends Credentials {


    @Before
    public void setUp() {
        baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteCourier() {
        if(courierId > 0) {
            deleteCourierAccount();
        }
    }


    @Test
    @DisplayName("Успешная авторизация курьера в системе")
    public void successLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseLogIn = logInCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccount(responseLogIn);
        courierIdForDelete(courier);

        System.out.println(responseLogIn.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации под несуществующим пользователем")
    public void invalidAccountLoginCourierTest() {
        Response response = invalidCourierAccount();
        statusCodeAndBodyLogInCourierAccountWithInvalidData(response);

        System.out.println(response.body().asString());

    }


    @Test
    @DisplayName("Ошибка авторизации c невалидным паролем")
    public void invalidPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseInvalidPassword = logInInvalidPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithInvalidData(responseInvalidPassword);
        courierIdForDelete(courier);

        System.out.println(responseInvalidPassword.body().asString());
    }

    @Test
    @DisplayName("Ошибка авторизации c невалидным логином")
    public void invalidLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseInvalidLogin = logInInvalidLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithInvalidData(responseInvalidLogin);
        courierIdForDelete(courier);

        System.out.println(responseInvalidLogin.body().asString());

    }
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"login\"")
    public void nullLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseNullLogin = logInNullLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseNullLogin);
        courierIdForDelete(courier);

        System.out.println(responseNullLogin.body().asString());

    }

    //При запросе со значением null в поле "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации cо значением null в ключе \"password\"")
    public void nullPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseNullPassword = logInNullPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseNullPassword);
        courierIdForDelete(courier);

        System.out.println(responseNullPassword.body().asString());

    }

    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"login\"")
    public void bodyWithOutLoginLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseWithOutLogin = logInWithOutLoginCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseWithOutLogin);
        courierIdForDelete(courier);

        System.out.println(responseWithOutLogin.body().asString());

    }

    //При запросе без поля "пароль" сервер возвращает 504 ошибку, в том числе проверено через Postman.
    @Test
    @DisplayName("Ошибка авторизации при отсутствии в теле запроса пары ключ-значение \"password\"")
    public void bodyWithOutPasswordLoginInCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequestCreateCourierAccount(courier);
        statusCodeAndBodyCreatedCourierAccount(response);

        Response responseWithOutPassword = logInWithOutPasswordCourierAccount(courier);
        statusCodeAndBodyLogInCourierAccountWithOutData(responseWithOutPassword);
        courierIdForDelete(courier);

        System.out.println(responseWithOutPassword.body().asString());

    }



}


