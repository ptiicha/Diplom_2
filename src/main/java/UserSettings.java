import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import static org.hamcrest.Matchers.equalTo;


public class UserSettings extends BaseURL {
    private static final String PATH = "api/auth/";
    public String accessToken = "";

    //@Step("Create user") // Success, Failed
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(PATH + "register/")
                .then();
    }

    //@Step("Login")  // Login success, Login failed wrong password, Login failed wrong email
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH + "login/")
                .then();
    }

    //@Step("Get user data")
    public ValidatableResponse getUserData(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(getSpec())
                .when()
                .get(PATH + "user")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    //@Step("User edit (authorized)")
    public Response userEditAuthorized(String accessToken, User user) {
        return given()
                .header("authorization", accessToken)
                .spec(getSpec())
                .when()
                .body(user)
                .patch(PATH + "user/");
    }

    //@Step("User edit (not authorized)")
    public Response userEditNotAuthorized(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .patch(PATH + "user/");
    }

    //@Step("User delete")
    public ValidatableResponse delete() {
        if (this.accessToken.equals("")) {
            return given()
                    .spec(getSpec())
                    .auth().oauth2(accessToken)
                    .delete(PATH + "user/")
                    .then();
        }
        return null;
    }
}

