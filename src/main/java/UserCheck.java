import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.Step;

public class UserCheck {
//регистрация
    @Step("Create new user success")
    public void userCreated(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }
    @Step("Create existed user failed")
    public void creationSameUserFailed(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(false))
                .and()
                .statusCode(403);
    }
    @Step("Login success")
    public void loggedIn(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(true))
                .and()
                .statusCode(200);
    }
    @Step("Login failed wrong password")
    public void notLoggedInvalidPassword(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(false))
                .and()
                .statusCode(401);
    }
    @Step("Login failed wrong email")
    public void notLoggedInvalidEmail(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(false))
                .and()
                .statusCode(401);
    }
}

