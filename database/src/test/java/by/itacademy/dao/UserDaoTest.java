package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends BaseDaoTest<User> {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }

    @Override
    protected User getModel() {
        return new User();
    }

    @Test
    public void getByLoginTest() {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        assertNotNull(user);
    }
}