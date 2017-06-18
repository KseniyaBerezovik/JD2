package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.otherEntity.Promotion;

public class PromotionDaoTest extends BaseDaoTest<Promotion> {

    private BaseDao<Promotion> dao = PromotionDao.getInstance();

    @Override
    protected BaseDao<Promotion> getDao() {
        return dao;
    }

    @Override
    protected Promotion getModel() {
        return new Promotion();
    }
}