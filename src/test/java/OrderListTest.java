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
    public void orderListTest() {
        Response response = getListOrdersWithoutCourierId();
        statusCodeAndBodygetListOrders(response);
        int ammount = getAmmountOrdersBody(response);

        System.out.println(response.body().asString());
        System.out.println(ammount);
    }

}
