import java.util.List;

public class Ingredients {
    public boolean success;
    public List<IngredientsData> ingredientsData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<IngredientsData> getIngredientsData() {
        return ingredientsData;
    }

    public void setData(List<IngredientsData> data) {
        this.ingredientsData = data;
    }
}