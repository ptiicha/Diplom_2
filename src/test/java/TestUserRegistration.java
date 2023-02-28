import io.restassured.response.ValidatableResponse;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestUserRegistration {
    private UserSettings userSet;
    private User userNew;
    private UserCheck check;

    @Before
    public void setup() {
        userSet = new UserSettings();
        check = new UserCheck();
    }

    @After
    public void deleteUser() {
        userSet.delete();
    }

    @Test
    @Step("Create user") // Success, Failed
    @DisplayName("Create new user success")
    public void userCreated() {
        userNew = UserGenerator.getRandomUser();
        ValidatableResponse createResponse = userSet.create(userNew);
        check.userCreated(createResponse);
        //accessToken = createResponse.extract().path("accessToken").toString().substring(6).trim();
        //sameEmail = createResponse.extract().path("email").toString().substring(6).trim();
    }
    @Test
    @DisplayName("Create existed user failed")
    public void creationSameUserFailed () {
        userNew = UserGenerator.getRandomUser();
        userSet.create(userNew);
        ValidatableResponse createResponse = userSet.create(userNew);
        check.creationSameUserFailed(createResponse);
    }
}
