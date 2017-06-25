package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.otherEntity.QReview;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl extends BaseDaoImpl<Review> implements ReviewDao {

    @Override
    public List<Review> getByProduct(Product product) {
        QReview review = new QReview("review");
        JPAQuery<Review> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        List<Review> reviews = query.select(review)
                .from(review)
                .where(review.product.eq(product))
                .fetchResults()
                .getResults();
        return reviews.size() > 0 ? reviews : null;
    }
}