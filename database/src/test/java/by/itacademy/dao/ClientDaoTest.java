package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.userEntity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

public class ClientDaoTest extends BaseDaoTest<Client> {

    private BaseDao<Client> dao = ClientDao.getInstance();

    @Override
    protected BaseDao<Client> getDao() {
        return dao;
    }

    @Override
    protected Client getModel() {
        return new Client();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }


    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}