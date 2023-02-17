import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserSettings extends BaseURL {
    private static final String REG = "api/auth/register"; // регистрация
    private static final String LOG = "api/auth/login"; // авторизация
    private static final String CHANGE = "api/auth/user"; // изменение данных

    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REG)
                .then();
    }

    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(LOG)
                .then();
    }

    public static ValidatableResponse change(String login, String password) {
        String json = String.format("{\"email\": \"login\", \"password\": \"password\"}", login, password);
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .patch(CHANGE)
                .then();
    }

    public static ValidatableResponse delete(String login) {
        String json = String.format("{\"id\": \"%d\"}", login);
        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .delete(CHANGE)
                .then();
    }
}
