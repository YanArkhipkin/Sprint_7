import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginNegativeTest {
    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
    }

    @Test
    @DisplayName("Invalid login when logging in")
    public void courierInvalidLoginTest() {
        Credentials credentials = new Credentials(courier.getLogin() + "1", courier.getPassword()); //передаем неверный логин
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 404;
        String message = responseLogin.extract().path("message");
        String expectedMessage = "Учетная запись не найдена";
        Assert.assertEquals("StatusCode should be 404", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("Invalid password when logging in")
    public void courierInvalidPasswordTest() {
        Credentials credentials = new Credentials(courier.getLogin(), courier.getPassword() + "1"); //передаем неверный пароль
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 404;
        String message = responseLogin.extract().path("message");
        String expectedMessage = "Учетная запись не найдена";
        Assert.assertEquals("StatusCode should be 404", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("Invalid login and password when logging in")
    public void courierInvalidPasswordAndLoginTest() {
        Credentials credentials = new Credentials(courier.getLogin() + "1", courier.getPassword() + "1"); //передаем неверный логин и пароль
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 404;
        String message = responseLogin.extract().path("message");
        String expectedMessage = "Учетная запись не найдена";
        Assert.assertEquals("StatusCode should be 404", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("Login field is null")
    public void courierLoginWhereLoginIsNullTest() {
        Credentials credentials = new Credentials(null, courier.getPassword() + "1"); //передаем null в логин
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 400;
        String message = responseLogin.extract().path("message");
        String expectedMessage = "Недостаточно данных для входа";
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("Password field is null") //тест падает, если поле пароль пустое
    public void courierLoginWherePasswordIsNullTest() {
        Credentials credentials = new Credentials(courier.getLogin(), null); //передаем null в пароль
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 400;
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
    }

    @Test
    @DisplayName("Login and password fields are null") //тест падает, если поле пароль пустое
    public void courierLoginWherePasswordAndLoginAreNullTest() {
        Credentials credentials = new Credentials(null, null); //передаем null в логин и в пароль
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        int expectedStatusCode = 400;
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
    }
}
