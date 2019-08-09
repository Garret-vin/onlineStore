package com.controller;

import com.model.Basket;
import com.model.Code;
import com.model.Order;
import com.model.User;
import com.service.BasketService;
import com.service.CodeService;
import com.service.MailService;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/user/payment")
public class OrderController {

    private MailService mailService;
    private OrderService orderService;
    private CodeService codeService;
    private BasketService basketService;

    @Autowired
    public OrderController(MailService mailService, OrderService orderService,
                           CodeService codeService, BasketService basketService) {
        this.mailService = mailService;
        this.orderService = orderService;
        this.codeService = codeService;
        this.basketService = basketService;
    }

    @GetMapping
    public ModelAndView showPaymentPage() {
        return new ModelAndView("payment", "order", new Order());
    }

    @PostMapping
    public String createOrder(@ModelAttribute("order") Order order,
                              @AuthenticationPrincipal User user) {
        codeService.add(new Code(user));
        Code code = null;
        Optional<Code> optionalCode = codeService.getLastCodeForUser(user);
        if (optionalCode.isPresent()) {
            code = optionalCode.get();
        }

        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }

        order.setUser(user);
        order.setBasket(basket);
        order.setCode(code);
        orderService.add(order);
        new Thread(() -> mailService.sendConfirmCode(order)).start();
        return "redirect:/user/payment/confirm";
    }

    @GetMapping("/confirm")
    public String showConfirmOrderPage() {
        return "payment_confirm";
    }

    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam("confirm") String confirm,
                               @AuthenticationPrincipal User user,
                               Model model) {
        Optional<Order> optionalOrder = orderService.getLastOrderForUser(user);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getCode().getValue().equals(confirm)) {
                Basket basket = new Basket(order.getUser());
                basketService.add(basket);
                model.addAttribute("message", "Покупка успешно совершена!");
            } else {
                model.addAttribute("message", "Неверный код. Введите заново!");
            }
        }
        return "payment_confirm";
    }
}
