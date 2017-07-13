package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.*;

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
        List<Product> products = new ArrayList<>();
        for(Characteristic characteristic : characteristics) {
            List<Product> resultList = getSessionFactory().getCurrentSession()
                    .createQuery("select c.product from Characteristic c where c.detail.id=:detailId " +
                            "and c.value=:currentValue", Product.class)
                    .setParameter("detailId", characteristic.getDetail().getName())
                    .setParameter("currentValue", characteristic.getValue())
                    .getResultList();
            products.addAll(resultList);
        }
        return products;
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

    @Override
    public List<Product> getByIntervalPrice(Integer priceFrom, String priceTo) {
        if(priceTo.equals("undefined")) {
            List<Product> products = getSessionFactory().getCurrentSession()
                    .createQuery("select p from Product p where p.price >= :priceFrom", Product.class)
                    .setParameter("priceFrom", Double.valueOf(priceFrom))
                    .getResultList();
            return products;
        } else {
            if(priceFrom == null) {
                priceFrom = 0;
            }
            List<Product> products = getSessionFactory().getCurrentSession()
                    .createQuery("select p from Product p where p.price >= :priceFrom and p.price <= :priceTo", Product.class)
                    .setParameter("priceFrom", Double.valueOf(priceFrom))
                    .setParameter("priceTo", Double.valueOf(priceTo))
                    .getResultList();
            return products;
        }
    }


    @Override
    public List<Product> testCriteria(Map<Long, List<String>> detailValueMap) {

        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Characteristic> characteristic = criteria.from(Characteristic.class);
        Join<Characteristic, Detail> detail = characteristic.join("detail");
        Join<Characteristic, Product> product = characteristic.join("product");

        criteria.select(product);

        for(Map.Entry<Long, List<String>> entry : detailValueMap.entrySet()) {
            criteria.where(cb.and(cb.equal(characteristic.get("detail").get("id"), entry.getKey()),
                                 characteristic.get("value").in(entry.getValue())
            ));
        }

        List<Product> products = getSessionFactory().getCurrentSession().createQuery(criteria).getResultList();
        System.out.println("PRODUCTS:");
        products.forEach(System.out::println);
        return products;
    }
}