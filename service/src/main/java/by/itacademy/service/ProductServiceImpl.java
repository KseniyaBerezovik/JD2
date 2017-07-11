package by.itacademy.service;

import by.itacademy.dao.ProductDao;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService  {

    private final static int PRODUCT_IN_PAGE = 3;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getByCategoryName(String name) {
        return productDao.getByCategoryName(name);
    }

    @Override
    public List<Product> getByNumberPageAndCount(Integer numberPage, Category category) {
        return productDao.getByNumberPageAndCount(numberPage, PRODUCT_IN_PAGE, category);
    }

    @Override
    public Integer getNextImageNumber() {
        return productDao.getNextImageNumber();
    }

    @Override
    public List<Integer> getPages(Category category) {
        Integer products = productDao.getCountOfProducts(category);
        int pageCount = (int) Math.ceil((double) products / PRODUCT_IN_PAGE);
        List<Integer> pages = new ArrayList<>();
        for(int i = 0; i < pageCount; i++) {
            pages.add(i + 1);
        }
        return pages;
    }
}