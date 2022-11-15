import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    private static final String CREATE_ORDER_PATH = "/api/v1/orders";
    private static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel";
    private static final String GET_ORDERS_PATH = "/api/v1/orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancel(int track) {
        String json = "{\"track\": " + track + "}";
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .put(CANCEL_ORDER_PATH)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .spec(getSpec())
                .get(GET_ORDERS_PATH)
                .then();
    }

}