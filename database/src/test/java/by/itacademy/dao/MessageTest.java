package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void messageSaveGetTest(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Message message = EntityBuilder.createMessage();
        session.persist(message);
        Long id = (Long) session.save(message);
        Message messageFromDB = session.get(Message.class, id);

        assertEquals(message, messageFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
