package by.itacademy.service;

import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;

import java.util.List;

public interface CartService extends BaseService<Cart> {
    List<Cart> getByUser(User user);
    void deleteProductFromCart(User user, Product product, Integer amount);
    void addToCart(User user, Product product, Integer amount);
    Integer getCountProductsInCart(User user);
    void cleanByUser(User user);

}
