package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Employee;

import static org.junit.Assert.assertEquals;

public class EmployeeTest extends BaseDaoTest<Employee> {

    private BaseDao<Employee> dao = EmployeeDao.getInstance();

    @Override
    protected BaseDao<Employee> getDao() {
        return dao;
    }

    @Override
    protected Employee getModel() {
        return new Employee();
    }
}