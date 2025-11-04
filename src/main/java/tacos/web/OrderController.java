package tacos.web;

import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid  @ModelAttribute("tacoOrder") TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            // Show the form again with error messages
            return "orderForm";
        }
        log.info("Order Submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
