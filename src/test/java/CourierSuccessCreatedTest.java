import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierSuccessCreatedTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
    }

    @Test
    @DisplayName("The courier was successfully created")
    public void courierCanBeCreatedTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        Assert.assertThat("id should be not null", id, notNullValue());
    }

    @Test
    @DisplayName("201 status code is returned, when creating a courier")
    public void courierStatusCodeIsCorrectTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 201;
        Assert.assertEquals("StatusCode should be 201", statusCode, expectedStatusCode);
    }

    @Test
    @DisplayName("A successful request returns ok: true")
    public void courierCreatedResponseBodyIsCorrectTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        Assert.assertEquals("Success request returns ok: true", isCourierCreated, true);
    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }

}
