import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.equalTo;

public class UserCheck {
//регистрация
    public void userCreated(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(true)).and().statusCode(200);
    }
    //регистрация завалена
    public void creationSameUserFailed(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(false)).and().statusCode(403);
    }
    // успешный логин
    public void loggedIn(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(true)).and().statusCode(200);
    }
//неуспешный логин с пустыми полями
    public void notLoggedRequiredFields(ValidatableResponse response) {
        response.assertThat()
                .body("success", equalTo(false)).and().statusCode(401);
    }
    //неуспешный логин с неправильным email
    public void notLoggedInvalidField(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false)).and().statusCode(401);
    }
}

