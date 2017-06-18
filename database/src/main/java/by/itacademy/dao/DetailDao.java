package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.QDetail;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

public class DetailDao extends BaseDao<Detail> {

    private DetailDao() {
        super(Detail.class);
    }

    private static DetailDao INSTANCE;

    public static DetailDao getInstance() {
        if(INSTANCE == null) {
            synchronized (DetailDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new DetailDao();
                }
            }
        }
        return INSTANCE;
    }

    public Detail getByName(Session session, String name) {
        QDetail detail = new QDetail("detail");
        JPAQuery<Detail> query = new JPAQuery<>(session);

        return query.select(detail).from(detail).where(detail.name.eq(name)).fetchOne();
    }
}