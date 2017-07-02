package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.productEntity.QProduct;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> getByCategoryName(String categoryName) {
        QProduct product = new QProduct("product");
        JPAQuery<Product> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        query.select(product)
                .from(product)
                .join(product.category)
                .where(product.category.name.eq(categoryName));

        return query.fetchResults().getResults();
    }

    @Override
    public List<Product> getByCharacteristics(List<Characteristic> characteristics) {
        return characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toList());
    }
}