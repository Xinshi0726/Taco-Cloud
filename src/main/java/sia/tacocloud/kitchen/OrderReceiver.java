package sia.tacocloud.kitchen;

import sia.tacocloud.tacos.TacoOrder;

public interface OrderReceiver {

  TacoOrder receiveOrder();

}