package sia.tacocloud.tacos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@Entity
public class Taco {
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();
    @Id
    private Long id;
    private Date createdAt = new Date();
}
