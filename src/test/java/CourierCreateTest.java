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
import static ru.praktikum.courier.CreateAccountCourierSteps.*;
import static ru.praktikum.courier.ResponseSteps.*;


public class CourierCreateTest extends Credentials{
   // private int courierId;

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
        @DisplayName("Успешное создание учетной записи курьера")
        public void successCreatedCourierTest () {
            Courier courier = Courier.random();
            Response response = sendPostRequestCreateCourierAccount(courier);
            statusCodeAndBodyCreatedCourierAccount(response);
            courierIdForDelete(courier);

            System.out.println(response.body().asString());
        }



    @Test
        @DisplayName("Ошибка при создании 2-х учетных записей с одинаковыми логинами")
        public void errorCreateIdenticalAccountsCourierTest () {
            Courier courier = Courier.random();
            Response response = sendPostRequestCreateCourierAccount(courier);
            statusCodeAndBodyCreatedCourierAccount(response);

            Response responseDouble = createIdenticalCourierAccount(courier);
            statusCodeAndBodyConflictIdenticalCourierAccount(responseDouble);
            courierIdForDelete(courier);

            System.out.println(responseDouble.body().asString());
        }

        @Test
        @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Login\"")
        public void errorCreateAccountWithoutLoginTest () {
            Response response = sendPostRequestCreateAccountWithoutLogin();
            statusCodeAndBodyBadRequestCreateAccountWithoutData(response);

            System.out.println(response.body().asString());

        }

        @Test
        @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"Password\"")
        public void errorCreateAccountWithoutPasswordTest () {
            Response response = sendPostRequestCreateAccountWithoutPassword();
            statusCodeAndBodyBadRequestCreateAccountWithoutData(response);

            System.out.println(response.body().asString());
        }
        @Test
        @DisplayName("Ошибка при создании учетной записи курьера с пустым полем \"firstName\"")
        public void errorCreateAccountWithoutFirstNameTest () {
            Response response = sendPostRequestCreateAccountWithoutFirstName();
            statusCodeAndBodyBadRequestCreateAccountWithoutData(response);

            System.out.println(response.body().asString());
        }


    }