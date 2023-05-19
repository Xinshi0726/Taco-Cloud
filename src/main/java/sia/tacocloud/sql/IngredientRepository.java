package sia.tacocloud.sql;
import sia.tacocloud.tacos.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
