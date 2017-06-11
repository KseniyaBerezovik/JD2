package by.itacademy.dao;

import by.itacademy.dao.util.EntityBuilder;
import by.itacademy.entity.userEntity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {
    private SessionFactory SESSION_FACTORY;
    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void employeeSaveTest(){
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = EntityBuilder.createEmployee();
        session.persist(employee);
        Long id = (Long) session.save(employee);
        Employee employeeFromDB = session.get(Employee.class, id);

        assertEquals(employee, employeeFromDB);

        transaction.commit();
        session.close();
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }
}
