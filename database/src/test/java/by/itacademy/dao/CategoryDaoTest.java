package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.productEntity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CategoryDaoTest extends BaseDaoTest<Category> {

    private BaseDao<Category> dao = CategoryDao.getInstance();

    @Override
    protected BaseDao<Category> getDao() {
        return dao;
    }

    @Override
    protected Category getModel() {
        return new Category();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void getByName() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Category category = CategoryDao.getInstance().getByName(session, "Мобильные телефоны");

        assertThat(category.getName(), is("Мобильные телефоны"));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}