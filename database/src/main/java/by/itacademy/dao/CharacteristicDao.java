package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;

import java.util.List;

public interface CharacteristicDao extends BaseDao<Characteristic> {
    List<Characteristic> getByProduct (Product product);
}
