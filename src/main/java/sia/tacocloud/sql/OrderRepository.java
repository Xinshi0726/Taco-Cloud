package sia.tacocloud.sql;

import org.springframework.stereotype.Repository;
import sia.tacocloud.tacos.TacoOrder;
import sia.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder,Long>{
    TacoOrder save(TacoOrder order);
}

