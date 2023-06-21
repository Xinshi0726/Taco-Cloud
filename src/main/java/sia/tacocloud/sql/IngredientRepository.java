package sia.tacocloud.sql;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.Ingredient;

import java.util.Optional;
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String>{
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
