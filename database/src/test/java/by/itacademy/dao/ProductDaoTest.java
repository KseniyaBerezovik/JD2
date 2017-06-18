package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.util.DataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ProductDaoTest extends BaseDaoTest<Product> {

    private BaseDao<Product> dao = ProductDao.getInstance();

    @Override
    protected BaseDao<Product> getDao() {
        return dao;
    }

    @Override
    protected Product getModel() {
        return new Product();
    }

    private SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        DataImporter.getInstance().importData(SESSION_FACTORY);
    }

    @Test
    public void testGetByCategoryName() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Product> products = ProductDao.getInstance()
                .getByCategoryName(session, "Мобильные телефоны");

        assertThat(products, hasSize(1));
        assertThat(products.get(0).getName(), is("Xiaomi Redmi 3"));

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetByCharacteristics() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Detail detail = DetailDao.getInstance().getByName(session, "Год выпуска");

        List<Characteristic> yearOfIssue = CharacteristicDao.getInstance().getByDetailAndValue(session, detail, "2017");
        List<Product> products = ProductDao.getInstance().getByCharacteristics(yearOfIssue);

        List<String> productNames = products.stream().map(p -> p.getName()).collect(toList());

        assertThat(products, hasSize(1));
        assertThat(productNames, contains("Xiaomi Redmi 3"));

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}