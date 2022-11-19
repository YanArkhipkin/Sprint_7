import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
    }

    @Test
    @DisplayName("The courier has been successfully logged in")
    public void courierSuccessLoginTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 200;
        Assert.assertThat("id should be not null", id, notNullValue());
        Assert.assertEquals("StatusCode should be 200", statusCode, expectedStatusCode);
    }

    @Test
    @DisplayName("Successful request returns id")
    public void courierSuccessLoginRequestReturnsIdTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        Assert.assertThat("id should be not null", id, notNullValue());
    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }
}
