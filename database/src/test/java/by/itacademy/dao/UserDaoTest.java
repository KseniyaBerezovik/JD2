package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.userEntity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class UserDaoTest extends BaseDaoTest<User> {

    @Autowired
    private UserDao dao;

    @Override
    protected BaseDao<User> getDao() {
        return dao;
    }

    @Override
    protected User getModel() {
        return new Client();
    }

    @Test
    public void testGetByLoginAndPassword() {
        getDataImporter().importData();
        User user = dao.getByLoginAndPassword("mivan", "1111");

        assertNotNull(user);
    }
}