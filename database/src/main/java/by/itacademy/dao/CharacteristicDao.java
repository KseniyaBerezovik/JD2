package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;

import java.util.List;

public interface CharacteristicDao extends BaseDao<Characteristic> {
    List<Characteristic> getByDetailAndValue (Detail detail, String value);
    List<Characteristic> getByDetailAndValueList (Detail detail, List<String> values);
    List<Characteristic> getByDetailAndIntervalValues (Detail detail, String from, String to);
    List<Characteristic> getByProduct (Product product);
}
