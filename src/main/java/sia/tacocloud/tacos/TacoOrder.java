package sia.tacocloud.tacos;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.validator.constraints.CreditCardNumber;
import sia.tacocloud.tacos.Taco;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
public class TacoOrder {
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Delivery street is required")
    private String deliveryStreet;

    @NotBlank(message = "Delivery city is required")
    private String deliveryCity;

    @NotBlank(message = "Delivery state is required")
    private String deliveryState;

    @NotBlank(message = "Delivery zip is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date PlacedAt;
    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
