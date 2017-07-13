package by.itacademy.controller;

import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.CartService;
import by.itacademy.service.ReviewService;
import by.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @ModelAttribute("countProductInCart")
    public Integer getCountProductInCar() {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userLogin);
        return cartService.getCountProductsInCart(user);
    }

    @PostMapping("/product/{id}")
    public String addReview(@RequestParam("content") String content,
                            @PathVariable("id") Long productID,
                            HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByLogin(name);
        Review review = reviewService.create(content, user.getId(), productID);
        reviewService.save(review);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
