package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;

import java.util.List;


public interface CartDao extends BaseDao<Cart> {
    List<Cart> getByUser(User user);
    Cart getCart(User user, Product product);
    Integer getCountProductsInCart(User user);
    void cleanByUser(User user);
}
