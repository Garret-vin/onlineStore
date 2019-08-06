package com.controller;

import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class BasketController {

    private BasketService basketService;
    private ProductService productService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping("/buy/product/{id}")
    public String showBasketSize(@PathVariable("id") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");

        Product product = null;
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        }

        Basket basket = null;
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        }

        basketService.addProduct(basket, product);
        return "redirect:/user/products";
    }
}
