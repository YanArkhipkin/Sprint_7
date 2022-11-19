import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("The list of orders is expected to be returned to the response body")
    public void getOrderListTest() {
        ValidatableResponse responseGetOrders = orderClient.getOrdersList();
        int statusCode = responseGetOrders.extract().statusCode();
        int expectedStatusCode = 200;
        List<String> ordersBody = responseGetOrders.extract().path("orders.id");
        Assert.assertEquals("StatusCode should be 200", statusCode, expectedStatusCode);
        Assert.assertThat("orders id should be not null", ordersBody, notNullValue());
    }
}