package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Client;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class ClientDaoTest extends BaseDaoTest<Client> {

    @Autowired
    private ClientDao dao;

    @Override
    protected BaseDao<Client> getDao() {
        return dao;
    }

    @Override
    protected Client getModel() {
        return new Client();
    }
}