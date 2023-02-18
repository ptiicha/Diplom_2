import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestUser {
    private String email;
    private String sameEmail;
    private UserSettings userSet;
    private User userNew;
    private UserCheck check;
    private String accessToken;

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        check = new UserCheck();
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(300);
    }

    @Test
    public void userCreated() {
        ValidatableResponse createResponse = userSet.create(userNew);
        check.userCreated(createResponse);
        accessToken = createResponse.extract().path("accessToken").toString().substring(6).trim();
        sameEmail = createResponse.extract().path("email").toString().substring(6).trim();
    }
    @Test
    public void creationSameUserFailed () {
        userSet.create(userNew);
        userNew.setEmail(sameEmail);
        User secondUser = UserGenerator.getRandomUser();
        secondUser.setEmail(sameEmail);
        ValidatableResponse createResponse = userSet.create(secondUser);
        check.creationSameUserFailed(createResponse);
    }

    @Test
    public void loggedIn () {
        userSet.create(userNew);
        UserCredentials credentials = UserCredentials.from(userNew);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.loggedIn(loginResponse);
        email = loginResponse.extract().path("email");
    }

    @Test
    public void notLoggedRequiredFields () {
        userSet.create(userNew);
        email = userSet.login(UserCredentials.from(userNew)).extract().path("email");
        UserCredentials credentials = UserCredentials.from(userNew);
        credentials.setEmail(null);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedRequiredFields(loginResponse);
    }

    @Test
    public void notLoggedInvalidField () {
        userSet.create(userNew);
        email = userSet.login(UserCredentials.from(userNew)).extract().path("email");
        UserCredentials credentials = UserCredentials.from(userNew);
        credentials.setEmail("null@mail.ru");
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedInvalidField(loginResponse);
    }

    @After
    public void deleteUser() {
        userSet.delete();
    }
}
