package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.util.DataImporter;
import by.itacademy.entity.productEntity.Characteristic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CharacteristicDaoTest extends BaseDaoTest<Characteristic> {

    private BaseDao<Characteristic> dao = CharacteristicDao.getInstance();

    @Override
    protected BaseDao<Characteristic> getDao() {
        return dao;
    }

    @Override
    protected Characteristic getModel() {
        return new Characteristic();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }


    @Test
    public void testGetByDetailAndIntervalValues(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Detail detail = DetailDao.getInstance().getByName(session, "Год выпуска");

        List<Characteristic> characteristics =
                CharacteristicDao.getInstance().getByDetailAndIntervalValues(session, detail, "2015", "2017");

        assertThat(characteristics, hasSize(2));

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetByDetailAndValue() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Detail detail = DetailDao.getInstance().getByName(session, "Год выпуска");
        List<Characteristic> characteristics =
                CharacteristicDao.getInstance().getByDetailAndValue(session, detail, "2017");

        assertThat(characteristics, hasSize(1));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}