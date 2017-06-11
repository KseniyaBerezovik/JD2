package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.orderEntity.OrderContent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderContentTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void orderContentSaveGetTest() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        OrderContent orderContent = EntityBuilder.createOrderContent();
        session.persist(orderContent);
        Long id = (Long) session.save(orderContent);
        OrderContent orderContentFromDB = session.get(OrderContent.class, id);

        Assert.assertEquals(orderContent, orderContentFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
