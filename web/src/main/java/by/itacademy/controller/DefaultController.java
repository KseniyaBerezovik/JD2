package by.itacademy.controller;

import by.itacademy.service.CharacteristicService;
import by.itacademy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CharacteristicService characteristicService;

//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
}
