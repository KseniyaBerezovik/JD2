package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.orderEntity.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void orderSaveGetTest() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Order order = EntityBuilder.createOrder();
        session.persist(order);
        Long id = (Long) session.save(order);
        Order orderFromDB = session.get(Order.class, id);

        Assert.assertEquals(order, orderFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}