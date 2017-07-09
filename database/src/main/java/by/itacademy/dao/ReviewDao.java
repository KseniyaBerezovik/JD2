package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;

import java.util.List;

public interface ReviewDao extends BaseDao<Review> {
    List<Review> getByProduct(Product product);
}