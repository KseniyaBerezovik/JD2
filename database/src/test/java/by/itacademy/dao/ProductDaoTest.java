package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class ProductDaoTest extends BaseDaoTest<Product> {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DetailDao detailDao;

    @Autowired
    private CharacteristicDao characteristicDao;

    @Override
    protected BaseDao<Product> getDao() {
        return productDao;
    }

    @Override
    protected Product getModel() {
        return new Product();
    }

    @Test
    public void testGetByCategoryName() {
        getDataImporter().importData();
        List<Product> products = productDao.getByCategoryName("Мобильные телефоны", 0);

        assertThat(products, hasSize(1));
        assertThat(products.get(0).getName(), is("Xiaomi Redmi 3"));
    }

//    @Test
//    public void testGetByCharacteristics() {
//        getDataImporter().importData();
//        Detail detail = detailDao.getByName("Год выпуска");
//
//        List<Characteristic> yearOfIssue = characteristicDao.getByDetailAndValue(detail, "2017");
//        List<Product> products = productDao.getByCharacteristics(yearOfIssue);
//        System.out.println("PRODUCTS : " + products);
//
//        List<String> productNames = new ArrayList<>();
//
//        for(Product product : products) {
//            productNames.add(product.getName());
//        }
//
//        assertThat(products, hasSize(1));
//        assertThat(productNames, contains("Xiaomi Redmi 3"));
//    }
}