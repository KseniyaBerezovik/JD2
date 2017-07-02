package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    @Override
    public Category getByName(String name) {
        List<Category> categories = getSessionFactory().getCurrentSession().createQuery("select c from Category c where c.name=:name", Category.class)
                .setParameter("name", name)
                .getResultList();
        return categories.isEmpty() ? null : categories.get(0);
    }
}
