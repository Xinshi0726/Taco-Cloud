package sia.tacocloud.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.sql.JdbcOrderRepository;
import sia.tacocloud.sql.OrderRepository;
import sia.tacocloud.tacos.TacoOrder;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm()
    {
        return "orderForm";
    }

    private final OrderRepository orderRepository;
    @PostMapping("/submitted")
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){
        if (errors.hasErrors())
        {
            return "orderForm";
        }
        this.orderRepository.save(order);
        log.info("Order Submitted: {}",order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
