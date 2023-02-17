import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class UserCheck {
//регистрация
    public void userCreated(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201);
    }
    //регистрация завалена
    public void creationSameUserFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", notNullValue());
    }
    // успешный логин
    public void loggedIn(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }
//неуспешный логин с пустыми полями
    public void notLoggedRequiredFields(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("message", notNullValue());
    }
    //неуспешный логин с неправильным email
    public void notLoggedInvalidField(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("message", notNullValue());
    }
}

