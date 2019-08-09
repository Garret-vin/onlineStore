package com.controller;

import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    private UserService userService;
    private BasketService basketService;
    private ProductService productService;

    @Autowired
    public InitController(UserService userService,
                          BasketService basketService,
                          ProductService productService) {
        this.userService = userService;
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login";
        } else if ("ROLE_ADMIN".equals(user.getRole())) {
            return "redirect:/admin/user";
        } else {
            return "redirect:/user/product";
        }
    }

    @GetMapping("/init")
    public String init() {
        User admin = new User("test", "test@test", "test", "ROLE_ADMIN");
        User user = new User("user", "garret.ork@gmail.com", "user", "ROLE_USER");
        userService.add(admin);
        userService.add(user);
        Basket basket = new Basket(user);
        basketService.add(basket);

        Product product = new Product("baton", "black", 12.34);
        productService.add(product);
        Product product2 = new Product("bread", "white", 42.33);
        productService.add(product2);
        Product product3 = new Product("milk", "cows", 20.90);
        productService.add(product3);
        return "index";
    }
}
