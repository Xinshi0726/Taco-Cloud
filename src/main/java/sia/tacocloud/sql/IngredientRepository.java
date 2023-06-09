package sia.tacocloud.sql;
import sia.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.Ingredient;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
