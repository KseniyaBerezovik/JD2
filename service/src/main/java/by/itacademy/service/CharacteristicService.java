package by.itacademy.service;

import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseService;

import java.util.List;

public interface CharacteristicService extends BaseService<Characteristic> {
    List<Characteristic> getByProduct(Product product);
}
