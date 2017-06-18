package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.otherEntity.Promotion;

public class PromotionDao extends BaseDao<Promotion> {

    private PromotionDao() {
        super(Promotion.class);
    }

    private static PromotionDao INSTANCE;

    public static PromotionDao getInstance() {
        if(INSTANCE == null) {
            synchronized (PromotionDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new PromotionDao();
                }
            }
        }
        return INSTANCE;
    }
}
