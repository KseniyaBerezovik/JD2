package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.QCategory;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    @Override
    public Category getByName(String name) {
        QCategory category = new QCategory("category");
        JPAQuery<Category> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        query.select(category).from(category).where(category.name.eq(name));

        return query.fetchOne();
    }
}
