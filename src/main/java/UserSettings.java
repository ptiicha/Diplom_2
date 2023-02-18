import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import static org.hamcrest.Matchers.equalTo;


public class UserSettings extends BaseURL {
    private static final String PATH = "api/auth";
    public String accessToken = "";

    //@Step("User create")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/register")
                .then();
    }

    //@Step("User logged in")
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
    public ValidatableResponse userEditAuthorized(String accessToken, User user) {
        return given()
                .header("authorization", "bearer " + accessToken)
                .spec(getSpec())
                .body(user)
                .when()
                .patch(PATH + "user/")
                .then();
    }

    //@Step("User edit (not authorized)")
    public ValidatableResponse userEditNotAuthorized(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .patch(PATH + "user/")
                .then();
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

