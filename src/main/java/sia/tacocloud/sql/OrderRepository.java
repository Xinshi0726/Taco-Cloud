package sia.tacocloud.sql;
import java.util.Optional;
import sia.tacocloud.tacos.TacoOrder;
public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}

