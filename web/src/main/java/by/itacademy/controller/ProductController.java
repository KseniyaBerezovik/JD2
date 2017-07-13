package by.itacademy.controller;

import by.itacademy.dto.FilterDto;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @GetMapping("/category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long id,
                                        @RequestParam(value = "year", required = false) List<Integer> years,
                                        @RequestParam(value = "yearFrom", required = false) Integer yearFrom,
                                        @RequestParam(value = "yearTo", required = false) Integer yearTo,
                                        @RequestParam(value = "priceFrom", required = false) Integer priceFrom,
                                        @RequestParam(value = "priceTo", required = false, defaultValue = "undefined") String priceTo,
                                        @RequestParam(value = "os", required = false) List<String> os,
                                        Model model) {
        Category category = categoryService.getByID(id);
        model.addAttribute("category", category);
        List<Detail> details = category.getDetails();
        model.addAttribute("details", details);

        boolean allParamsUndefined = years == null &&
                                      yearFrom == null &&
                                      yearTo == null &&
                                    priceFrom == null &&
                                    priceTo.equals("undefined") &&
                                    os == null;

        List<Product> products;

        if(allParamsUndefined) {
            products = productService.getByCategoryName(category.getName());
        } else {
            FilterDto filterDto = new FilterDto(years, yearFrom, yearTo, priceFrom, priceTo, os);
            products = productService.getByFilter(filterDto);
        }


//        boolean osParamUndefined = os == null;
//
//        if(yearParamsUndefined && priceParamsUndefined && osParamUndefined) {
//            products.addAll(productService.getByCategoryName(category.getName()));
//        } else {
//            if(!osParamUndefined){
//                Detail detail = detailService.getByID(3L);
//                products = productService.getByDetailAndValueList(detail, os);
//            }
//
//            if(!yearParamsUndefined && !priceParamsUndefined){
//                Set<Product> productsToAddByYear = productService.getProductsByYears(years, yearFrom, yearTo);
//                Set<Product> productsToAddByPrice = productService.getProductsByPrice(priceFrom, priceTo);
//                products = productsToAddByYear.stream().filter(productsToAddByPrice::contains).collect(Collectors.toSet());
//            } else {
//                if(!yearParamsUndefined) {
//                    products = productService.getProductsByYears(years, yearFrom, yearTo);
//                }
//                if(!priceParamsUndefined) {
//                    products = productService.getProductsByPrice(priceFrom, priceTo);
//                }
//            }
//        }
//
        model.addAttribute("products", products);
        return "main_page";
    }

    @GetMapping("/product_info/{id}")
    public String getProductInfo(@PathVariable("id") Long id, Model model) {
        Product product = productService.getByID(id);
        List<Review> reviews = reviewService.getByProduct(product);
        List<Characteristic> characteristics = characteristicService.getByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("characteristics", characteristics);
        return "product_info";
    }
}