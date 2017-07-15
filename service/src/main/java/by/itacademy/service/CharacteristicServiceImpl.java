package by.itacademy.service;

import by.itacademy.dao.CharacteristicDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacteristicServiceImpl extends BaseServiceImpl<Characteristic> implements CharacteristicService {
    @Autowired
    private CharacteristicDao characteristicDao;

    @Override
    public List<Characteristic> getByProduct(Product product) {
        return characteristicDao.getByProduct(product);
    }

    @Override
    public List<Characteristic> getByDetailAndIntervalValues(Detail detail, String from, String to) {
        return characteristicDao.getByDetailAndIntervalValues(detail, from, to);
    }

    @Override
    public List<Characteristic> getByDetailAndValue(Detail detail, String value) {
        return characteristicDao.getByDetailAndValue(detail, value);
    }

    @Override
    public List<Characteristic> getByDetailAndValueList(Detail detail, List<String> values) {
        return characteristicDao.getByDetailAndValueList(detail, values);
    }
}