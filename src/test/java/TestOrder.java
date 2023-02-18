import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class TestOrder {
    public User user;
    public UserSettings userSet;
    public OrderSettings oderSet;
    public Order oder;
    public Ingredients allIngredients;
    public IngredientsSettings ingredientsSet;
    private String accessToken;
    public List<String> ingredients;
    ValidatableResponse response;

    @Before
    public void setup() {
        ingredientsSet = new IngredientsSettings();
        allIngredients = ingredientsSet.getIngredients();
        ingredients = new ArrayList<>();
        ingredients.add(allIngredients.ingredientsData.get(0).get_id());
        ingredients.add(allIngredients.ingredientsData.get(1).get_id());
        ingredients.add(allIngredients.ingredientsData.get(2).get_id());
        user = UserGenerator.getRandomUser();
        userSet = new UserSettings();
        userSet.create(user);
        ValidatableResponse responseForToken = userSet.getUserData(accessToken);
        accessToken = responseForToken.extract().path("accessToken");
    }


    @After
    public void deleteUser(){
        userSet.delete();
    }

}
