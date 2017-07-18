package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.entity.userEntity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class OrderContentDaoTest extends BaseDaoTest<OrderContent> {

    @Autowired
    private OrderContentDao orderContentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void getByOrderTest() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        List<Order> orders = orderDao.getByUser(user);
        List<OrderContent> contents = orderContentDao.getByOrder(orders.get(0));
        assertThat(contents, hasSize(1));
    }

    @Override
    protected BaseDao<OrderContent> getDao() {
        return orderContentDao;
    }

    @Override
    protected OrderContent getModel() {
        return new OrderContent();
    }
}