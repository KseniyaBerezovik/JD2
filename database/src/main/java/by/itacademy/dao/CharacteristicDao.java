package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;

import java.util.List;

public interface CharacteristicDao extends BaseDao<Characteristic> {
    List<Characteristic> getByDetailAndValue(Detail detail, String value);
    List<Characteristic> getByDetailAndIntervalValues(Detail detail, String from, String to);
}
