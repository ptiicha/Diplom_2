import static io.restassured.RestAssured.given;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import io.qameta.allure.Step;

public class OrderSettings extends BaseURL {
    private static final String ORDER_PATH = "api/orders/";

    @Step("Create order authorized")
    public Response createOrderAuthorized(String accessToken, Order order) {
        return given()
                .header("Authorization", accessToken)
                .spec(getSpec())
                .when()
                .body(order)
                .post(ORDER_PATH);
    }

    @Step("Create order not authorized")
    public ValidatableResponse createOrderNotAuthorized(Order order) {
        return given()
                .spec(getSpec())
                .when()
                .body(order)
                .post(ORDER_PATH)
                .then();
    }

    @Step("Get order authorized")
    public ValidatableResponse getOrderAuthorized(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(getSpec())
                .get(ORDER_PATH)
                .then();
    }

    @Step("Get order not authorized")
    public ValidatableResponse getOrderNotAuthorized() {
        return given()
                .spec(getSpec())
                .get(ORDER_PATH)
                .then();
    }
}
