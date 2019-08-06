package com.controller;

import com.model.Basket;
import com.model.User;
import com.service.BasketService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private UserService userService;
    private BasketService basketService;

    @Autowired
    public UserController(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "users";
    }

    @GetMapping("/add")
    public ModelAndView showAddUserPage() {
        return new ModelAndView("addUser", "user", new User());
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @RequestParam("confirmPassword") String confirmPassword,
                                ModelMap model) {
        String email = user.getEmail();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getRole();
        Optional<User> optionalPresentUser = userService.getByLoginOrEmail(login, email);
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role == null) {
            model.addAttribute("error", "Empty fields!");
        } else if (optionalPresentUser.isPresent()) {
            model.addAttribute("error", "Пользователь с таким логином " +
                    "или электронной почтой уже зарегистрирован!");
        } else if (!password.equals(confirmPassword) || password.length() > 16) {
            model.addAttribute("error", "Passwords not equals or too long!");
        } else {
            userService.add(user);
            Optional<User> optionalNewUser = userService.getByLogin(login);
            if (optionalNewUser.isPresent()) {
                Basket basket = new Basket(optionalNewUser.get());
                basketService.add(basket);
            }
            return new ModelAndView("redirect:/admin/user");
        }
        return new ModelAndView("addUser", model);
    }

    @GetMapping("/change/{id}")
    public String showEditUserPage(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }
        return "change_user";
    }

    @PostMapping("/change")
    public String applyEditUser(@ModelAttribute("user") User user,
                                @RequestParam("confirmPassword") String confirmPassword,
                                ModelMap model) {
        String login = user.getLogin();
        String email = user.getEmail();
        String password = user.getPassword();
        String role = user.getRole();
        if (email.isEmpty() || login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            model.addAttribute("error", "Empty fields!");
            model.addAttribute("user", user);
        } else if (!(password.equals(confirmPassword))) {
            model.addAttribute("error", "Passwords not equals!");
            model.addAttribute("user", user);
        } else {
            userService.update(user);
            return "redirect:/admin/user";
        }
        return "change_user";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/admin/user";
    }
}
