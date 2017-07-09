package by.itacademy.service;

import by.itacademy.dao.CharacteristicDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}