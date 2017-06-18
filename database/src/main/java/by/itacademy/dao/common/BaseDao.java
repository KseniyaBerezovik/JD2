package by.itacademy.dao.common;

import by.itacademy.entity.otherEntity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BaseDao<T extends BaseEntity> {

    private static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T getByID(Long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        T entity = session.get(entityClass, id);

        transaction.commit();
        session.close();

        return entity;
    }

    public void save(T entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    public void update(T entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        session.update(entity);

        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();
        session.close();
    }

    public List<T> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        query.select(query.from(entityClass));
        List<T> resultList = session.createQuery(query).getResultList();

        transaction.commit();
        session.close();

        return resultList;
    }
}