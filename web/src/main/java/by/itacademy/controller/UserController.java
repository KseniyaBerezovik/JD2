package by.itacademy.controller;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.Profile;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderContentService orderContentService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProfileService profileService;

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("countProductInCart")
    public Integer getCountProductInCar() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        return cartService.getCountProductsInCart(user);
    }

    @GetMapping("/orders")
    public String addOrders(Model model) {
        return "user-orders";
    }

    @GetMapping("/create-order")
    public String addOrder() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        orderService.createOrder(user);
        return "order-success";
    }

    @GetMapping("/my-orders")
    public String seeOrders(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        List<Order> orders = orderService.getByUser(user);
        if(orders.size() == 0){
            model.addAttribute("empty", true);
        } else {
            List<List<OrderContent>> contents = new ArrayList<>();
            for(Order order : orders) {
                contents.add(orderContentService.getByOrder(order));
            }
            model.addAttribute("contents", contents);
            model.addAttribute("orders", orders);
        }
        return "my-orders";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        Profile profile = profileService.getByUser(user);
        if (profile != null) {
            model.addAttribute("profile", profile);
        }
        return "user-profile";
    }

    @GetMapping("/create-profile")
    public String createProfile(Model model) {
        model.addAttribute("profile", new Profile());
        return "user-create-profile";
    }

    @PostMapping("/create-profile")
    public String addProfile(Profile profile) {
        System.out.println("PROFILE: " + profile);
        return "redirect:profile";
    }
}