import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class TestOrder {
    public User user;
    public UserSettings userSet;
    public OrderSettings orderSet;
    public Order order;
    public Ingredients allIngredients;
    public IngredientsSettings ingredientsSet;
    private String accessToken;
    public List<String> ingredients;
    Response responseCreate;
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
        ValidatableResponse response = userSet.create(user);
        accessToken = response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Create order (not authorized user))")
    public void createOrderNotAuthorized() {
        orderSet = new OrderSettings();
        order = new Order(ingredients);
        response = orderSet.createOrderNotAuthorized(order);
        response.assertThat().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create order (authorized user))")
    public void createOrderAuthorized() {
        orderSet = new OrderSettings();
        order = new Order(ingredients);
        responseCreate = orderSet.createOrderAuthorized(accessToken, order);
        responseCreate.then().assertThat().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create order without ingredients")
    public void createOderWithNullIngredient() {
        ingredients.clear();
        orderSet = new OrderSettings();
        order = new Order(ingredients);
        responseCreate = orderSet.createOrderAuthorized(accessToken, order);
        responseCreate.then().assertThat().statusCode(400);
    }

    @After
    public void deleteUser(){
        userSet.delete();
    }

}
