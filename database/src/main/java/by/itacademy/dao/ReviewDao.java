package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.otherEntity.QReview;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

public class ReviewDao extends BaseDao<Review> {

    private ReviewDao() {
        super(Review.class);
    }

    private static ReviewDao INSTANCE;

    public static ReviewDao getInstance() {
        if(INSTANCE == null) {
            synchronized (ReviewDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ReviewDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Review> getByProduct(Session session, Product product) {
        QReview review = new QReview("review");
        JPAQuery<Review> query = new JPAQuery<>(session);

        List<Review> reviews = query.select(review)
                .from(review)
                .where(review.product.eq(product))
                .fetchResults()
                .getResults();
        return reviews;
    }
}