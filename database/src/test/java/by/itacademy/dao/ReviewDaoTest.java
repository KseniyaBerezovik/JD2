package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.otherEntity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


public class ReviewDaoTest extends BaseDaoTest<Review> {

    private BaseDao<Review> dao = ReviewDao.getInstance();

    @Override
    protected BaseDao<Review> getDao() {
        return dao;
    }

    @Override
    protected Review getModel() {
        return new Review();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void testGetByProduct() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = ProductDao.getInstance().getByID(1L);

        List<Review> reviews = ReviewDao.getInstance().getByProduct(session, product);

        assertThat(reviews, hasSize(1));
        assertThat(reviews.get(0).getContent(), is("Very good!"));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}