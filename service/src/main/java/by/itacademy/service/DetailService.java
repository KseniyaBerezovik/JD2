package by.itacademy.service;

import by.itacademy.entity.productEntity.Detail;
import by.itacademy.service.common.BaseService;

public interface DetailService extends BaseService<Detail> {
    Detail getByName(String name);
}
