package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.orderEntity.Order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
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
    public void testGetByClient() {
        getDataImporter().importData();
        Client client = (Client) userDao.getByLoginAndPassword("mivan", "1111");
        List<Order> orders = orderDao.getByClient(client);

        assertThat(orders, hasSize(1));
        Assert.assertEquals(orders.get(0).getOwner(), client);
    }

    @Test
    public void testGetByDate() {
        getDataImporter().importData();
        List<Order> orders = orderDao.getByDate(
                LocalDateTime.of(2017, 4, 5, 0, 0),
                LocalDateTime.of(2017, 6, 5, 0, 0));

        assertThat(orders, hasSize(1));
    }
}