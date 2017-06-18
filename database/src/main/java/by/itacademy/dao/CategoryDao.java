package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.QCategory;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

public class CategoryDao extends BaseDao<Category> {

    private CategoryDao() {
        super(Category.class);
    }

    private static CategoryDao INSTANCE;

    public static CategoryDao getInstance() {
        if(INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new CategoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public Category getByName(Session session, String name) {
        QCategory category = new QCategory("category");
        JPAQuery<Category> query = new JPAQuery<>(session);

        query.select(category).from(category).where(category.name.eq(name));

        return query.fetchOne();
    }
}
