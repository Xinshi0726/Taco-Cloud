package sia.tacocloud.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.TacoOrder;

@Component
@Slf4j
public class KitchenUI {

  public void displayOrder(TacoOrder order) {
    // TODO: Beef this up to do more than just log the received taco.
    //       To display it in some sort of UI.
    log.info("RECEIVED ORDER:  " + order);
  }
  
}
