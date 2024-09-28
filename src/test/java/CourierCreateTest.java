import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.courier.Courier;

import static io.restassured.RestAssured.baseURI;
import static ru.praktikum.courier.CreateAccountCourierSteps.*;
import static ru.praktikum.courier.ResponseSteps.*;


public class CourierCreateTest {

    @Before
    public void setUp() {
        baseURI = "https://qa-scooter.praktikum-services.ru/";
    }


    @Test
    @DisplayName("Успешное создание учетной записи курьера")
    public void Success_CreatedCourierTest() {

        Response response = sendPostRequest_CreateCourierAccount(Courier.random());
        statusCodeAndBody_CreatedCourierAccount(response);

        System.out.println(response.body().asString());
    }


    @Test
    @DisplayName("Ошибка при создании 2-х учетных записей с одинаковыми логинами")
    public void error_CreateIdenticalAccountsCourierTest() {
        Courier courier = Courier.random();
        Response response = sendPostRequest_CreateCourierAccount(courier);
        statusCodeAndBody_CreatedCourierAccount(response);

        Response responseDouble = create_IdenticalCourierAccount(courier);
        statusCodeAndBodyConflict_IdenticalCourierAccount(responseDouble);

        System.out.println(responseDouble.body().asString());
    }

    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Login\"")
    public void error_CreateAccountWithoutLoginTest() {
        Response response = sendPostRequest_CreateAccountWithoutLogin();
        statusCodeAndBody_BadRequest_CreateAccountWithoutData(response);

        System.out.println(response.body().asString());

    }

    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Password\"")
    public void error_CreateAccountWithoutPasswordTest() {
        Response response = sendPostRequest_CreateAccountWithoutPassword();
        statusCodeAndBody_BadRequest_CreateAccountWithoutData(response);

        System.out.println(response.body().asString());
    }
    @Test
    @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"firstName\"")
    public void error_CreateAccountWithoutFirstNameTest() {
        Response response = sendPostRequest_CreateAccountWithoutFirstName();
        statusCodeAndBody_BadRequest_CreateAccountWithoutData(response);
        System.out.println(response.body().asString());
    }


}