package com.controller;

import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.service.BasketService;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;
    private BasketService basketService;

    @Autowired
    public ProductController(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
    }

    @GetMapping("/admin/products")
    public String showAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "products_admin";
    }

    @GetMapping("/admin/add/product")
    public ModelAndView showAddProductPage() {
        return new ModelAndView("addProduct", "product", new Product());
    }

    @PostMapping("/admin/add/product")
    public String addProduct(@ModelAttribute Product product) {
        if (product.getPrice() == null) {
            product.setPrice(0.0);
        }
        productService.add(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/change/product/{id}")
    public String showChangeProductPage(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
        }
        return "change_product";
    }

    @PostMapping("/admin/change/product")
    public String changeProduct(@ModelAttribute Product product) {
        if (product.getPrice() == null) {
            product.setPrice(0.0);
        }
        productService.update(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/delete/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/user/products")
    public String showAllUserProducts(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Optional<Basket> optionalBasket = basketService.getBasketByUser(user);
        optionalBasket.ifPresent(basket ->
                model.addAttribute("size", basketService.size(basket)));
        model.addAttribute("productList", productService.getAll());
        return "products_user";
    }
}
