package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Product;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<Long> testCriteria(Map<Long, List<String>> detailValueMap) {

        StringBuilder nativeQuery = new StringBuilder("select p.id as id from products p " +
                "LEFT JOIN characteristics c1 ON p.id=c1.product_id AND c1.detail_id=1 " +
                "LEFT JOIN characteristics c2 ON p.id=c2.product_id AND c2.detail_id=2 " +
                "LEFT JOIN characteristics c3 ON p.id=c3.product_id AND c3.detail_id=3 " +
                "LEFT JOIN characteristics c4 ON p.id=c4.product_id AND c4.detail_id=4 " +
                "WHERE ");

        List<String> forAddingToQuery = new ArrayList<>();
        for(Map.Entry<Long, List<String>> entry : detailValueMap.entrySet()) {
            if(entry.getKey() == 4L) {
                if(entry.getValue().size() == 2) {
                    forAddingToQuery.add("c4.value >= " + entry.getValue().get(0));
                    forAddingToQuery.add("c4.value <= " + entry.getValue().get(1));
                } else {
                    if(entry.getValue().get(0).split(":")[0].equals("FROM")) {
                        forAddingToQuery.add("c4.value >= " + entry.getValue().get(0).split(":")[1]);
                    }
                    if(entry.getValue().get(0).split(":")[0].equals("TO")) {
                        forAddingToQuery.add("c4.value <= " + entry.getValue().get(0).split(":")[1]);
                    }
                }
            } else {
                String num = String.valueOf(entry.getKey());
                forAddingToQuery.add("c" + num + ".value IN(?" + num +")");
            }
        }
        String toAdding = String.join(" AND ", forAddingToQuery);

        String fullNativeQuery = nativeQuery + toAdding;
        System.out.println("QUERY: " + fullNativeQuery);

        NativeQuery query = getSessionFactory().getCurrentSession().createNativeQuery(fullNativeQuery);

        if(detailValueMap.containsKey(1L)) {
            query.setParameter(1, detailValueMap.get(1L));
        }
        if(detailValueMap.containsKey(3L)) {
            query.setParameter(3, detailValueMap.get(3L));
        }

        return (List<Long>) query.list().stream().map(i -> Long.valueOf(Objects.toString(i))).collect(Collectors.toList());
    }
}
