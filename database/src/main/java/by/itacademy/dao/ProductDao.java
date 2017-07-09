package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> getByCategoryName(String categoryName);
    List<Product> getByCharacteristics(List<Characteristic> characteristics);
    List<Product> getByNumberPageAndCount(Integer numberPage, Integer numberOfProductInPage);
    Integer getNextImageNumber();
}
