package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Detail;
import org.springframework.stereotype.Repository;

@Repository
public class DetailDaoImpl extends BaseDaoImpl<Detail> implements DetailDao {

    public Detail getByName(String name) {
        Detail detail = getSessionFactory().getCurrentSession().
                createQuery("select d from Detail d where d.name=:name", Detail.class)
                .setParameter("name", name)
                .getResultList()
                .get(0);

        return detail;
    }
}