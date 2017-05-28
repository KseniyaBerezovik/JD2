package by.itacademy.dao;

import by.itacademy.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDao {
    public static Client getClientByID(long id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.get(Client.class, id);
        transaction.commit();
        session.close();
        sessionFactory.close();
        return client;
    }
}
