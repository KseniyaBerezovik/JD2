package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.productEntity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void productSaveGetTest() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = EntityBuilder.createProduct();
        session.persist(product);
        Long id = (Long) session.save(product);
        Product productFromDB = session.get(Product.class, id);

        assertEquals(product, productFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
