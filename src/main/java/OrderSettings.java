import static io.restassured.RestAssured.given;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;

public class OrderSettings extends BaseURL {
    private static final String ORDER_PATH = "/api/orders/";

    @Step("Create order authorized")
    public ValidatableResponse createOrderAuthorized(String accessToken, Order oder) {
        return given()
                .header("Authorization", accessToken)
                .spec(getSpec())
                .when()
                .body(oder)
                .post(ORDER_PATH)
                .then();
    }

    @Step("Create order not authorized")
    public ValidatableResponse createOrderNotAuthorized(Order oder) {
        return given()
                .spec(getSpec())
                .when()
                .body(oder)
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
