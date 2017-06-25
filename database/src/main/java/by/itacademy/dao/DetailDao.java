package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Detail;

public interface DetailDao extends BaseDao<Detail> {
    Detail getByName(String name);
}
