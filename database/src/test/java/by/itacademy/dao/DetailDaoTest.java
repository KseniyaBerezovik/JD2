package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.productEntity.Detail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DetailDaoTest extends BaseDaoTest<Detail> {

    private BaseDao<Detail> dao = DetailDao.getInstance();

    @Override
    protected BaseDao<Detail> getDao() {
        return dao;
    }

    @Override
    protected Detail getModel() {
        return new Detail();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void testGetByName() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Detail detail = DetailDao.getInstance().getByName(session, "Год выпуска");

        assertThat(detail.getName(), is("Год выпуска"));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}