package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class OrderDaoTest extends BaseDaoTest<Order> {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<Order> getDao() {
        return orderDao;
    }

    @Override
    protected Order getModel() {
        return new Order();
    }

    @Test
    public void getByUserTest() {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        List<Order> orders = orderDao.getByUser(user);
        assertThat(orders, hasSize(2));
    }
}