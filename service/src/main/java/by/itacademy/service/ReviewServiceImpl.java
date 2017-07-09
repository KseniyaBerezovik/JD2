package by.itacademy.service;

import by.itacademy.dao.ReviewDao;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Review> getByProduct(Product product) {
        return reviewDao.getByProduct(product);
    }

    @Override
    public Review create(String content, Long userID, Long productID) {
        Review review = new Review();
        review.setDateOfCreation(LocalDateTime.now());
        review.setContent(content);
        review.setProduct(productService.getByID(productID));
        review.setOwner(userService.getByID(userID));
        return review;
    }
}
