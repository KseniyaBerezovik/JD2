package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.orderEntity.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class OrderDaoTest extends BaseDaoTest<Order> {

    private BaseDao<Order> dao = OrderDao.getInstance();

    @Override
    protected BaseDao<Order> getDao() {
        return dao;
    }

    @Override
    protected Order getModel() {
        return new Order();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void testGetByClient() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = ClientDao.getInstance().getByID(1L);
        List<Order> orders = OrderDao.getInstance().getByClient(session, client);

        assertThat(orders, hasSize(1));
        Assert.assertEquals(orders.get(0).getOwner(), client);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetByDate() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Order> orders = OrderDao.getInstance().getByDate(session,
                LocalDateTime.of(2017, 4, 5, 0, 0),
                LocalDateTime.of(2017, 6, 5, 0, 0));

        assertThat(orders, hasSize(1));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}