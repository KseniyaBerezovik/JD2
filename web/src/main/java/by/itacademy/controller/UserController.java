package by.itacademy.controller;

import by.itacademy.dao.OrderContentDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.OrderContentService;
import by.itacademy.service.OrderService;
import by.itacademy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderContentService orderContentService;
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
        List<List<OrderContent>> contents = new ArrayList<>();
        for(Order order : orders) {
            contents.add(orderContentService.getByOrder(order));
        }
        System.out.println("CONTENTS: " + contents);
        model.addAttribute("contents", contents);
        model.addAttribute("orders", orders);
        return "my-orders";
    }
}