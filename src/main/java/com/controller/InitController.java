package com.controller;

import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import com.service.UserService;
import com.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("user")
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

    @ModelAttribute("user")
    public User setUserToSession(User user) {
        return user;
    }

    @GetMapping("/")
    public String init() {
        SecurityContext context = SecurityContextHolder.getContext();
        for (GrantedAuthority authority : context.getAuthentication().getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_ADMIN".equals(role)) {
                return "redirect:/admin/user";
            } else if ("ROLE_USER".equals(role)){
                return "redirect:/user/product";
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }

    /*@PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @ModelAttribute("user") User user,
                        Model model) {
        String saltedPassword = "";
        User registeredUser = null;
        Optional<User> optionalUser = userService.getByLogin(login);
        if (optionalUser.isPresent()) {
            registeredUser = optionalUser.get();
            saltedPassword = HashUtil.getSaltedPassword(password, registeredUser.getSalt());
        }

        if (registeredUser != null && registeredUser.getPassword().equals(saltedPassword)) {
            user.setId(registeredUser.getId());
            user.setLogin(registeredUser.getLogin());
            user.setPassword(registeredUser.getPassword());
            user.setEmail(registeredUser.getEmail());
            user.setRole(registeredUser.getRole());
            user.setSalt(registeredUser.getSalt());
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin/user";
            } else {
                return "redirect:/user/product";
            }
        } else {
            model.addAttribute("error", "Пользователь с таким логином и паролем не найден");
            return "index";
        }
    }*/

    @GetMapping("/init")
    public String addUser() {
        User admin = new User("test", "test@test", "test", "admin");
        User user = new User("user", "garret.ork@gmail.com", "user", "user");
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
