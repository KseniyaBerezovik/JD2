package by.itacademy.service;

import by.itacademy.dao.CartDao;
import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl extends BaseDaoImpl<Cart> implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public List<Cart> getByUser(User user) {
        return cartDao.getByUser(user);
    }

    @Override
    public void deleteProductFromCart(User user, Product product, Integer amount) {
        Cart cart = cartDao.getCart(user, product);
        Integer currentAmount = cart.getAmount();

        if(amount == currentAmount) {
            cartDao.delete(cart);
        } else if(amount < currentAmount) {
            cart.setAmount(currentAmount - amount);
            cartDao.update(cart);
        } else {
            throw new IllegalStateException("Incorrect amount of product to be removed");
        }
    }

    @Override
    public void addToCart(User user, Product product, Integer amount) {
        List<Cart> carts = cartDao.getByUser(user);
        boolean isProductPresent = false;
        for(Cart cart : carts) {
            if(cart.getProduct().getId() == product.getId()){
                isProductPresent = true;
                Integer currentAmount = cart.getAmount();
                cart.setAmount(currentAmount + amount);
                cartDao.update(cart);
                break;
            }
        }
        if(!isProductPresent) {
            Cart cart = new Cart();
            cart.setAmount(amount);
            cart.setProduct(product);
            cart.setOwner(user);
            cartDao.save(cart);
        }
    }

    @Override
    public Integer getCountProductsInCart(User user) {
        return cartDao.getCountProductsInCart(user);
    }
}