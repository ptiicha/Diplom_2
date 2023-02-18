import io.restassured.response.ValidatableResponse;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestUserLogin {
    private UserSettings userSet;
    private User userNew;
    private UserCheck check;

    @Before
    public void setUp() {
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
        userSet.create(userNew);
        check = new UserCheck();
    }

    @After
    public void deleteUser() {
        userSet.delete();
    }

    @Test
    @DisplayName("Login success")
    public void loggedIn () {
        UserCredentials credentials = UserCredentials.from(userNew);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.loggedIn(loginResponse);
    }

    @Test
    @DisplayName("Login failed wrong password")
    public void notLoggedInvalidPassword () {
        userNew.setPassword("test");
        UserCredentials credentials = UserCredentials.from(userNew);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedInvalidPassword(loginResponse);
    }

    @Test
    @DisplayName("Login failed wrong email")
    public void notLoggedInvalidEmail () {
        userNew.setEmail("null@mail.ru");
        UserCredentials credentials = UserCredentials.from(userNew);
        ValidatableResponse loginResponse = userSet.login(credentials);
        check.notLoggedInvalidEmail(loginResponse);
    }
}
