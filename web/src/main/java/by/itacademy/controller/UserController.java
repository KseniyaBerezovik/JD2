package by.itacademy.controller;

import by.itacademy.entity.userEntity.User;
import by.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getAll")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/user_get_all";
    }

    @GetMapping("/user/see_profile/{id}")
    public String seeProfile(@PathVariable("id") Long id, Model model) {
        User user = userService.getByID(id);
        model.addAttribute("user", user);
        return "user_profile";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteProfile(@PathVariable("id") Long id, Model model) {
        User user = userService.getByID(id);
        userService.delete(user);
        model.addAttribute("user", user);
        return "user_delete_info";
    }
}
