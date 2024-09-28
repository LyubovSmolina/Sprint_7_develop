import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.order.OrdersData;

import java.util.List;

import static ru.praktikum.order.OrderSteps.sendPostRequest_OrderSelectScooterColor;
import static ru.praktikum.order.OrderSteps.statusCodeAndBody_OrderSelectScooterColor;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] OrderCreateTest() {
        return new Object[][] {
                {List.of()},
                {List.of("GREY", "BLACK")},
                {List.of("GREY")},
                {List.of("BLACK")},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Успешное создание заказа при учете в теле запроса цвета самоката: 2 параметра(серый и черный), одного из параметров(серый/черный), без выбора цвета")
    public void scooterColorTest() {
        OrdersData colorSelect = OrdersData.createNewOrder_SelectColor(color);
        Response response = sendPostRequest_OrderSelectScooterColor(colorSelect);
        statusCodeAndBody_OrderSelectScooterColor(response);

        System.out.println(color);
        System.out.println(response.body().asString());
    }

}
