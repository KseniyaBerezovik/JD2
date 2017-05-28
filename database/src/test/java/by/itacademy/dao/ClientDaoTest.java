package by.itacademy.dao;


import by.itacademy.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientDaoTest {
    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void getClientTest() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = new Client();
        client.setName("name");
        client.setSurname("surname");
        long id =(long) session.save(client);

        Client clientFromBD = session.get(Client.class, id);
        Assert.assertEquals(client.getName(), clientFromBD.getName());
        Assert.assertEquals(client.getSurname(), clientFromBD.getSurname());

        System.out.println(client);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}