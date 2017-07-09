package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
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
    public List<Product> getByNumberPageAndCount(Integer numberPage, Integer numberOfProductInPage) {
        int start = numberPage * numberOfProductInPage;
        List<Product> products = getSessionFactory().getCurrentSession()
                .createQuery("select p from Product p", Product.class)
                .setFirstResult(start)
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
        System.out.println("КАРТИНКА: " + image);
        String[] split = image.split("\\.");
        System.out.println("РАЗМЕР: " + split.length);
        System.out.println("ПЕРВЫЙ ЭЛЕМЕНТ: " + split[0]);
        return Integer.valueOf(split[0]) + 1;
    }
}