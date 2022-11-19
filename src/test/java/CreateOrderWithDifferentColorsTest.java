import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderWithDifferentColorsTest {
    private OrderClient orderClient;
    private Order order;
    private int track;

    public CreateOrderWithDifferentColorsTest(Order order) {
        this.order = order;
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {OrderGenerator.getOrderColorWithoutColor()},
                {OrderGenerator.getOrderColorGrey()},
                {OrderGenerator.getOrderColorBlackAndGrey()},
                {OrderGenerator.getDefaultOrder()}
        };
    }

    @Test
    @DisplayName("The order can be created with different color combinations")
    public void orderCanBeCreatedWithDifferentColorsTest() {
        ValidatableResponse responseCreate = orderClient.create(order);
        track = responseCreate.extract().path("track");
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 201;
        Assert.assertEquals("StatusCode should be 201", statusCode, expectedStatusCode);
        Assert.assertThat("track should be not null", track, notNullValue());
    }

    @After
    public void cleanUp() {
        orderClient.cancel(track);
    }
}