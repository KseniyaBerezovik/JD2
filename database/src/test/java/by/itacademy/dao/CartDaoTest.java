package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


public class CartDaoTest extends BaseDaoTest<Cart> {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Override
    protected BaseDao<Cart> getDao() {
        return cartDao;
    }

    @Override
    protected Cart getModel() {
        return new Cart();
    }

    @Test
    public void getByUser() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        List<Cart> carts = cartDao.getByUser(user);
        assertThat(carts, hasSize(2));
    }

    @Test
    public void getCart() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        Product product = productDao.getByID(1L);
        Cart cart = cartDao.getCart(user, product);
        assertThat(cart.getAmount(), is(2));
    }

    @Test
    public void getCountProductsInCart() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        Integer countProductsInCart = cartDao.getCountProductsInCart(user);
        assertThat(countProductsInCart, is(4));
    }

    @Test
    public void cleanByUser() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        cartDao.cleanByUser(user);
        List<Cart> carts = cartDao.getByUser(user);
        assertThat(carts, hasSize(0));
    }
}