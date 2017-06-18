package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.userEntity.User;
import by.itacademy.util.DataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends BaseDaoTest<User> {

    private BaseDao<User> dao = UserDao.getInstance();

    @Override
    protected BaseDao<User> getDao() {
        return dao;
    }

    @Override
    protected User getModel() {
        return new Client();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void testGetByLoginAndPassword() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        User user = UserDao.getInstance().getByLoginAndPassword(session, "mivan", "1111");

        assertNotNull(user);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
