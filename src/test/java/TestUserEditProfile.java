import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class TestUserEditProfile {
    private UserSettings userSet;
    private User userNew;
    private UserCheck check;
    private String accessToken;

    @Before
    public void setup() {
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
        ValidatableResponse response = userSet.create(userNew);
        accessToken = response.extract().path("accessToken");
        check = new UserCheck();
    }

    @After
    public void deleteUser(){
        userSet.delete();
    }

    @Test
    @DisplayName("User edit email (authorized)")
    public void userEditAuthorized() {
        userSet.getUserData(accessToken);
        userNew.setEmail("asya@mail.list");
        Response changeResponse = userSet.userEditAuthorized(accessToken, new User(userNew.getEmail(), userNew.getName()));
        changeResponse.then().assertThat().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("User edit name (authorized)")
    public void userEditNameAuthorized() {
        userSet.getUserData(accessToken);
        userNew.setName("Маша");
        Response response = userSet.userEditAuthorized(accessToken, new User(userNew.getEmail(), userNew.getName()));
        response.then().assertThat().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("User edit name (not authorized)")
    public void userEditNameNotAuthorized() {
        userSet.getUserData(accessToken);
        userNew.setName("Маша");
        Response response = userSet.userEditNotAuthorized(new User(userNew.getEmail(), userNew.getName()));
        response.then().assertThat().statusCode(401).body("success", equalTo(false)).and();
    }

    @Test
    @DisplayName("User edit email (not authorized)")
    public void userEditLoginNotAuthorized() {
        userSet.getUserData(accessToken);
        userNew.setEmail("nastya@mail.ru");
        Response response = userSet.userEditNotAuthorized(new User(userNew.getEmail(), userNew.getName()));
        response.then().assertThat().statusCode(401).and().body("success", equalTo(false));
    }
}
