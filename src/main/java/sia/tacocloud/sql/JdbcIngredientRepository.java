package sia.tacocloud.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
     private final JdbcTemplate jdbcTemplate;

     @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
         return new Ingredient(
                 row.getString("id"),
                 row.getString("name"),
                 Ingredient.Type.valueOf(row.getString("type")));
     }

     @Override
    public Iterable<Ingredient> findAll(){
         return jdbcTemplate.query("SELECT id, name, type from Ingredient",this::mapRowToIngredient);
     }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id,name,type FROM Ingredient WHERE id = ?", this::mapRowToIngredient, id));
    }

    @Override
     public Ingredient save(Ingredient ingredient)
     {
        jdbcTemplate.update("INSERT INTO Ingredient (id,name,type) values (?,?,?)",ingredient.getId(),ingredient.getName(),ingredient.getType().toString());
        return ingredient;
     }
}
