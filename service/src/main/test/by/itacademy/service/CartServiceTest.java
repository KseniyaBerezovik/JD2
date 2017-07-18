package by.itacademy.service;

import by.itacademy.common.BaseServiceTest;
import by.itacademy.config.ServiceConfig;
import by.itacademy.config.TestServiceConfig;
import by.itacademy.dao.CartDao;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.Cart;
import by.itacademy.service.common.BaseService;
import by.itacademy.service.common.BaseServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class CartServiceTest extends BaseServiceTest<Cart> {

    @Mock
    private CartDao cartDao;

    @Autowired
    @InjectMocks
    private CartService cartService;

    @Override
    protected BaseService<Cart> getService() {
        System.out.println("CART SERVICE: " + cartService);
        return cartService;
    }

    @Override
    protected BaseDao<Cart> getDao() {
        System.out.println("CART DAO: " + cartDao);
        return cartDao;
    }

    @Override
    protected Cart getModel() {
        return new Cart();
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByUser() throws Exception {
    }
}