package com.controller;

import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import com.service.UserService;
import com.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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
    public String init() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        String saltedPassword = "";

        User registeredUser = null;
        Optional<User> optionalUser = userService.getByLogin(login);
        if (optionalUser.isPresent()) {
            registeredUser = optionalUser.get();
            saltedPassword = HashUtil.getSaltedPassword(password, registeredUser.getSalt());
        }

        if (registeredUser != null && registeredUser.getPassword().equals(saltedPassword)) {
            model.addAttribute("user", registeredUser);
            session.setAttribute("user", registeredUser);
            if ("admin".equals(registeredUser.getRole())) {
                return "redirect:/admin/users";
            } else {
                return "redirect:/user/products";
            }
        } else {
            model.addAttribute("error", "Пользователь с таким логином и паролем не найден");
            return "index";
        }
    }

    /*@GetMapping("/init")
    public String addUser() {
        User admin = new User("test", "test@test", "test", "test", "admin");
        User user = new User("user", "garret.ork@gmail.com", "user", "user", "user");
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
    }*/
}
