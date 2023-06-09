package sia.tacocloud.sql;
import java.util.Optional;
import sia.tacocloud.tacos.TacoOrder;
import sia.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Ingredient,String>{
    TacoOrder save(TacoOrder order);
}

