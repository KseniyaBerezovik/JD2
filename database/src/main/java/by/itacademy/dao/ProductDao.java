package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDao extends BaseDao<Product> {

    private ProductDao() {
        super(Product.class);
    }

    private static ProductDao INSTANCE;

    public static ProductDao getInstance() {
        if(INSTANCE == null) {
            synchronized (ProductDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ProductDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Product> getByCategoryName(Session session, String categoryName) {
        QProduct product = new QProduct("product");
        JPAQuery<Product> query = new JPAQuery<>(session);

        query.select(product)
                .from(product)
                .join(product.category)
                .where(product.category.name.eq(categoryName));

        return query.fetchResults().getResults();
    }

    public List<Product> getByCharacteristics(List<Characteristic> characteristics) {
        return characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toList());
    }
}