import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestUser {
    private String userId;
    private UserSettings userSet;
    private User userNew;
    private UserCheck check;

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        check = new UserCheck();
        userSet = new UserSettings();
        userNew = UserGenerator.getRandom();
    }

    @Test
    public void userCreated() {
        ValidatableResponse createResponse = userSet.create(userNew);
        check.userCreated(createResponse);
        userId = userSet.login(UserCredentials.from(userNew)).extract().path("id");
    }
    @Test
    public void creationSameCourierFailed () {
        userNew.setLogin("SameLogin");
        userSet.create(userNew);
        User secondUser = UserGenerator.getRandom();
        secondUser.setLogin("SameLogin");
        ValidatableResponse createResponse = userSet.create(secondUser);
        check.creationSameUserFailed(createResponse);

        userId = userSet.login(UserCredentials.from(userNew)).extract().path("id");
    }

    @Test
    public void loggedIn () {
        userSet.create(userNew);
        UserCredentials credentials = UserCredentials.from(userNew);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.loggedIn(loginResponse);
        userId = loginResponse.extract().path("id");
    }

    @Test
    public void notLoggedRequiredFields () {
        userSet.create(userNew);
        userId = userSet.login(UserCredentials.from(userNew)).extract().path("id");
        UserCredentials credentials = UserCredentials.from(userNew);
        credentials.setLogin(null);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedRequiredFields(loginResponse);
    }

    @Test
    public void notLoggedInvalidField () {
        userSet.create(userNew);
        userId = userSet.login(UserCredentials.from(userNew)).extract().path("id");
        UserCredentials credentials = UserCredentials.from(userNew);
        credentials.setLogin("Wrong login");
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedInvalidField(loginResponse);
    }
}
