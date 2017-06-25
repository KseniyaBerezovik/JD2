package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Employee;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class EmployeeTest extends BaseDaoTest<Employee> {

    @Autowired
    private EmployeeDao dao;

    @Override
    protected BaseDao<Employee> getDao() {
        return dao;
    }

    @Override
    protected Employee getModel() {
        return new Employee();
    }
}