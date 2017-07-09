package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> getByCategoryName(String categoryName) {
        List<Product> products = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p where p.category.name=:name", Product.class)
                .setParameter("name", categoryName)
                .getResultList();
        return products;
    }

    @Override
    public List<Product> getByCharacteristics(List<Characteristic> characteristics) {
        return null;
    }

    @Override
    public List<Product> getByNumberPageAndCount(Integer numberPage, Integer numberOfProductInPage, Category category) {
        List<Product> products = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p where p.category.id=:id order by p.id asc", Product.class)
                .setParameter("id", category.getId())
                .setFirstResult(numberOfProductInPage * ((numberPage - 1) + 1))
                .setMaxResults(numberOfProductInPage)
                .getResultList();
        return products;
    }

    @Override
    public Integer getNextImageNumber() {
        Product product = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p where p.image is not null order by p.id desc", Product.class)
                .setMaxResults(1)
                .getSingleResult();
        String image = product.getImage();
        String[] split = image.split("\\.");
        return Integer.valueOf(split[0]) + 1;
    }

    @Override
    public Integer getCountOfProducts(Category category) {
        Long count = getSessionFactory().getCurrentSession()
                .createQuery("select count(*) from Product as p where p.category.id=:id", Long.class)
                .setParameter("id", category.getId())
                .getSingleResult();
        return Math.toIntExact(count);
    }
}
