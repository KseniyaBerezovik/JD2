package by.itacademy.service;

import by.itacademy.dao.DetailDao;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl extends BaseServiceImpl<Detail> implements DetailService {

    @Autowired
    private DetailDao detailDao;

    @Override
    public Detail getByName(String name) {
        return detailDao.getByName(name);
    }
}
