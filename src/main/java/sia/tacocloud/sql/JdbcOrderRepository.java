package sia.tacocloud.sql;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacocloud.tacos.IngredientRef;
import sia.tacocloud.tacos.Taco;
import sia.tacocloud.tacos.TacoOrder;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
@SessionAttributes("tacoOrder")
public class JdbcOrderRepository implements OrderRepository {

    private JdbcTemplate jdbcTemplate;

     public JdbcOrderRepository(JdbcTemplate jdbcTemplate)
     {
         this.jdbcTemplate = jdbcTemplate;
     }
    @Override
    @Transactional
    public TacoOrder save(TacoOrder order){
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                        "INSERT INTO taco_order " +
                        "(delivery_name, delivery_street,delivery_city,delivery_state, delivery_zip, cc_number,cc_expiration,cc_cvv,placed_at) "+
                        "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(psc,keyHolder);
        long orderId = ((Number) keyHolder.getKeys().get("id")).longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco:tacos)
        {
            saveTaco(orderId,i++,taco);
        }
        return order;
    }

    private long saveTaco(long orderId, int orderKey, Taco taco)
    {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Taco "
                    +"(name, created_at,taco_order,taco_order_key) "
                    +"values (?,?,?,?)", Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCreatedAt(),
                        orderId,
                        orderKey
                ));
        pscf.setReturnGeneratedKeys(true);
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc,generatedKeyHolder);
        long tacoId = ((Number) generatedKeyHolder.getKeys().get("id")).longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId,taco.getIngredients());
        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientsRef)
        {
            int key = 0;
            for (IngredientRef ingredientRef:ingredientsRef)
            {
                jdbcTemplate.update("INSERT INTO ingredient_ref (ingredient,taco,taco_key) values (?,?,?)",ingredientRef.getIngredient(),tacoId,key+1);
            }
        }
}
