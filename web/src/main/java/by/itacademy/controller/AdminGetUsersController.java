package by.itacademy.controller;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.OrderService;
import by.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminGetUsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin-get-all-users";
    }

    @GetMapping("/see-profile/{id}")
    public String seeProfile(@PathVariable("id") Long id, Model model) {
        User user = userService.getByID(id);
        model.addAttribute("user", user);
        return "admin-get-user-profile";
    }

    @GetMapping("/all-orders/{userId}")
    public String seeOrders(@PathVariable("userId") Long userId, Model model) {
        User user = userService.getByID(userId);
        List<Order> orders = orderService.getByUser(user);
        model.addAttribute("orders", orders);
        System.out.println("ORDER DETAIL: " + orders.get(0).getDetail());
        return "admin-get-orders";
    }
}
