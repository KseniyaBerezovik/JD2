package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ReviewTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void reviewSaveGetTest(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Review review = EntityBuilder.createReview();
        session.persist(review);
        Long id = (Long) session.save(review);
        Review reviewFromDB = session.get(Review.class, id);

        assertEquals(review, reviewFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
