package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.QDetail;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

@Repository
public class DetailDaoImpl extends BaseDaoImpl<Detail> implements DetailDao {

    public Detail getByName(String name) {
        QDetail detail = new QDetail("detail");
        JPAQuery<Detail> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        return query.select(detail).from(detail).where(detail.name.eq(name)).fetchOne();
    }
}