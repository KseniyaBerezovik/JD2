package by.itacademy.service;

import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;

import java.util.List;

public interface ReviewService extends BaseService<Review> {
    List<Review> getByProduct(Product product);
    Review create(String content, Long userID, Long productID);
}
