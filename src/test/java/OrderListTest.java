import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static ru.praktikum.order.OrdersListSteps.*;


public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Успешное получение списка заказов без courierId")
    public void scooterColorTest() {
        Response response = getListOrders_WithoutCourierId();
        statusCodeAndBody_getListOrders(response);
        int ammount = getAmmountOrders_Body(response);

        System.out.println(response.body().asString());
        System.out.println(ammount);
    }

}
