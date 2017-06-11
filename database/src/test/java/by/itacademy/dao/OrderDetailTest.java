package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.orderEntity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderDetailTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void orderDetailSaveGetTest() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        OrderDetail orderDetail = EntityBuilder.createOrderDetail();
        session.persist(orderDetail);
        Long id = (Long) session.save(orderDetail);
        OrderDetail orderDetailFromDB = session.get(OrderDetail.class, id);

        Assert.assertEquals(orderDetail, orderDetailFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
