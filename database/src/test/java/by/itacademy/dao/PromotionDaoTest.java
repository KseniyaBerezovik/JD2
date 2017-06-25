package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.otherEntity.Promotion;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class PromotionDaoTest extends BaseDaoTest<Promotion> {

    @Autowired
    private PromotionDao dao;

    @Override
    protected BaseDao<Promotion> getDao() {
        return dao;
    }

    @Override
    protected Promotion getModel() {
        return new Promotion();
    }
}