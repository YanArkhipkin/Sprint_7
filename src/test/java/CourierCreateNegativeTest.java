import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourierCreateNegativeTest {
    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
    }

    @Test
    @DisplayName("You cannot create two identical couriers")
    public void createExistingCourierTest() {
        courier.setLogin("12345"); //существующий логин
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 409;
        String message = responseCreate.extract().path("message");
        String expectedMessage = "Этот логин уже используется. Попробуйте другой.";
        Assert.assertEquals("StatusCode should be 409", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("The login field is missing when creating a courier")
    public void createCourierWithoutLoginTest() {
        courier.setLogin(null); //логин отсутсвует
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 400;
        String message = responseCreate.extract().path("message");
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("The password field is missing when creating a courier")
    public void createCourierWithoutPasswordTest() {
        courier.setPassword(null); //пароль отсутсвует
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 400;
        String message = responseCreate.extract().path("message");
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

    @Test
    @DisplayName("There are no login and password fields when creating a courier")
    public void createCourierWithoutLoginAndPasswordTest() {
        //отсутствуют логин и пароль
        courier.setLogin(null);
        courier.setPassword(null);
        ValidatableResponse responseCreate = courierClient.create(courier);
        int statusCode = responseCreate.extract().statusCode();
        int expectedStatusCode = 400;
        String message = responseCreate.extract().path("message");
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        Assert.assertEquals("StatusCode should be 400", statusCode, expectedStatusCode);
        Assert.assertEquals("Message is incorrect", message, expectedMessage);
    }

}
