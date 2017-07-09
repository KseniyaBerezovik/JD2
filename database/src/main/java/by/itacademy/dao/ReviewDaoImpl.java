package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.otherEntity.Review;
import by.itacademy.entity.productEntity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl extends BaseDaoImpl<Review> implements ReviewDao {

    @Override
    public List<Review> getByProduct(Product product) {
        List<Review> reviews = getSessionFactory().getCurrentSession()
                .createQuery("select r from Review r where r.product.id=:id", Review.class)
                .setParameter("id", product.getId())
                .getResultList();
        return reviews;
    }
}