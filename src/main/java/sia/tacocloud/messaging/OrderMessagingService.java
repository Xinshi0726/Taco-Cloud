package sia.tacocloud.messaging;

import sia.tacocloud.tacos.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
