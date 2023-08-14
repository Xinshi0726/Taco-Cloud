package sia.tacocloud.sql;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.tacos.Taco;
import sia.tacocloud.tacos.TacoOrder;
import sia.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}

