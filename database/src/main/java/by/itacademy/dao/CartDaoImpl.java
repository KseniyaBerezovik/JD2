package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl extends BaseDaoImpl<Cart> implements CartDao {

    @Override
    public List<Cart> getByUser(User user) {
        List<Cart> carts = getSessionFactory().getCurrentSession()
                .createQuery("select c from Cart c where c.owner.id=:id", Cart.class)
                .setParameter("id", user.getId())
                .getResultList();
        return carts;
    }

    @Override
    public Cart getCart(User user, Product product) {
        Cart cart = getSessionFactory().getCurrentSession()
                .createQuery("select c from Cart c where c.owner.id=:userId and c.product.id=:productId", Cart.class)
                .setParameter("userId", user.getId())
                .setParameter("productId", product.getId())
                .getResultList().get(0);
        return cart;
    }

    @Override
    public Integer getCountProductsInCart(User user) {
        Long count = getSessionFactory().getCurrentSession()
                .createQuery("select sum(c.amount) from Cart c where c.owner.id=:id", Long.class)
                .setParameter("id", user.getId())
                .getSingleResult();
        return Math.toIntExact(count);
    }
}