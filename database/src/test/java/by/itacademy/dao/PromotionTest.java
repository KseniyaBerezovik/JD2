package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.Promotion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PromotionTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void promotionSaveGetTest(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Promotion promotion = EntityBuilder.createPromotion();
        session.persist(promotion);
        Long id = (Long) session.save(promotion);
        Promotion promotionFromDB = session.get(Promotion.class, id);

        assertEquals(promotion, promotionFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
