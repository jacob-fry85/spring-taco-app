package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;
import tacos.User;
import tacos.data.OrderRepo;
import tacos.data.UserRepo;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepo orderRepo;
    private UserRepo userRepo;

    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid  @ModelAttribute("tacoOrder") TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               Principal principal) {
        if(errors.hasErrors()) {
            // Show the form again with error messages
            return "orderForm";
        }
        log.info("Order Submitted: {}", order);

        User user = userRepo.findByUsername(principal.getName());
        if(user == null)  return "redirect:/";
        order.setUser(user);

        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
