import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class IngredientsSettings extends BaseURL {
    private static final String INGREDIENTS_PATH = "api/ingredients/";

    @Step("Get ingredients data")
    public Ingredients getIngredients() {
        return given()
                .spec(getSpec())
                .get(INGREDIENTS_PATH)
                .as(Ingredients.class);
    }
}
