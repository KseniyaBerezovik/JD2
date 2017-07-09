package by.itacademy.service;

import by.itacademy.dao.ProductDao;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService  {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getByCategoryName(String name) {
        return productDao.getByCategoryName(name);
    }

    @Override
    public List<Product> getByNumberPageAndCount(Integer numberPage, Integer numberOfProductInPage) {
        return productDao.getByNumberPageAndCount(numberPage, numberOfProductInPage);
    }

    @Override
    public Integer getNextImageNumber() {
        return productDao.getNextImageNumber();
    }

}