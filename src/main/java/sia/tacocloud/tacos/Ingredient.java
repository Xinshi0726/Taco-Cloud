package sia.tacocloud.tacos;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;
    public enum Type {
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
