package sia.tacocloud.tacos;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import sia.tacocloud.tacos.Ingredient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class Taco {
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;
    @Id
    private Long id;
    private Date createdAt = new Date();
}
