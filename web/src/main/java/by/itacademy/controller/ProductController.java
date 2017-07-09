package by.itacademy.controller;

import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.CategoryService;
import by.itacademy.service.CharacteristicService;
import by.itacademy.service.ProductService;
import by.itacademy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/main_page")
    public String mainPage(Model model) {
        List<Product> products = productService.getByNumberPageAndCount(1, 2);
        model.addAttribute("products", products);
        return "main_page";
    }

    @GetMapping("/main_page/{page}")
    public String mainPage(@PathVariable(value = "page") Integer page, Model model) {
        List<Product> products = productService.getByNumberPageAndCount(page, 2);
        model.addAttribute("products", products);
        return "main_page";
    }

    @GetMapping("/category/{name}")
    public String getProductsInCategory(@PathVariable("name") String name,
                                        Model model) {
        model.addAttribute("categoryName", name);
        List<Product> products = productService.getByCategoryName(name);
        model.addAttribute("products", products);
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