package by.itacademy.controller;

import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private DetailService detailService;

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

    @GetMapping("/main_page")
    public String defaultMainPage() {
        return "main_page";
    }

    @GetMapping("/main_page/{page}/{category_id}")
    public String mainPage(@PathVariable(value = "page") Integer page,
                           @PathVariable(value = "category_id") Long category_id,
                           Model model) {
        Category category = categoryService.getByID(category_id);
        List<Product> products = productService.getByNumberPageAndCount(page - 1, category);
        model.addAttribute("pages", productService.getPages(category));
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "main_page";
    }

    @GetMapping("/category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long id,
                                        @RequestParam(value = "year", required = false) List<Integer> years,
                                        @RequestParam(value = "yearFrom", required = false, defaultValue = "0") Integer yearFrom,
                                        @RequestParam(value = "yearTo", required = false, defaultValue = "3000") Integer yearTo,
                                        Model model) {
        List<Product> products = new ArrayList<>();

        if(yearFrom != null && yearTo != null) {
            Detail yearDetail = detailService.getByID(1L);
            List<Characteristic> characteristics = characteristicService.getByDetailAndIntervalValues(yearDetail, String.valueOf(yearFrom), String.valueOf(yearTo));
            System.out.println("CHARACTERISTICS: " + characteristics.size());
            characteristics.forEach(System.out::println);
            List<Product> productsInIntervalValue = characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toList());
            products.addAll(productsInIntervalValue);
        }

        if(years != null) {
            for(Integer year : years) {
                Detail yearDetail = detailService.getByID(1L);
                List<Characteristic> characteristics = characteristicService.getByDetailAndValue(yearDetail, String.valueOf(year));
                List<Product> productsInValue = characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toList());
                products.addAll(productsInValue);
            }
        }

        Category category = categoryService.getByID(id);
        model.addAttribute("category", category);
//        products.addAll(productService.getByNumberPageAndCount(0, category));
        model.addAttribute("products", products);
        List<Integer> pages = productService.getPages(category);
        model.addAttribute("pages", pages);
        List<Detail> details = category.getDetails();
        model.addAttribute("details", details);
        return "main_page";
    }

    @GetMapping("/product_info/{id}")
    public String getProductInfo(@PathVariable("id") Long id,
                                 Model model) {
        Product product = productService.getByID(id);
        List<Review> reviews = reviewService.getByProduct(product);
        List<Characteristic> characteristics = characteristicService.getByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("characteristics", characteristics);
        return "product_info";
    }
}