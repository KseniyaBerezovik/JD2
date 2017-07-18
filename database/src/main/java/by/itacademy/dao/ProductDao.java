package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductDao extends BaseDao<Product> {
    List<Product> getByCategoryName(String categoryName, int pageNumber, int countProductInPage);
    Integer getTotalPage(Category category, int countProductInPage);
    Integer getNextImageNumber();
    List<Long> getWithFilter(Map<Long, List<String>> detailValueMap, int pageNumber, int countProductInPage);
    Integer getTotalPageWithFilter(Map<Long, List<String>> detailValueMap, int countProductInPage);
}
