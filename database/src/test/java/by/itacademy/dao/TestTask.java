package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTask {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void taskSaveGetTest(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Task task = EntityBuilder.createTask();
        session.persist(task);
        Long id = (Long) session.save(task);
        Task taskFromDB = session.get(Task.class, id);

        assertEquals(task, taskFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
