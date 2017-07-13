package by.itacademy.service;

import by.itacademy.dao.ProductDao;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService  {

    private final static int PRODUCT_IN_PAGE = 3;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DetailService detailService;

    @Autowired
    private CharacteristicService characteristicService;

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

    @Override
    public List<Product> getByCharacteristics(List<Characteristic> characteristics) {
        return productDao.getByCharacteristics(characteristics);
    }

    @Override
    public Set<Product> getProductsByYears(List<Integer> years, Integer yearFrom, Integer yearTo) {
        Set<Product> productsToAdd = new HashSet<>();

        if(yearFrom != 0 || yearTo != 3000) {
            Detail yearDetail = detailService.getByID(1L);
            List<Characteristic> characteristics = characteristicService.getByDetailAndIntervalValues(yearDetail, String.valueOf(yearFrom), String.valueOf(yearTo));
            Set<Product> productsInIntervalValue = characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toSet());
            productsToAdd.addAll(productsInIntervalValue);
        }

        if(years != null) {
            for(Integer year : years) {
                Detail yearDetail = detailService.getByID(1L);
                List<Characteristic> characteristics = characteristicService.getByDetailAndValue(yearDetail, String.valueOf(year));
                Set<Product> productsInValue = characteristics.stream().map(ch -> ch.getProduct()).collect(Collectors.toSet());
                productsToAdd.addAll(productsInValue);
            }
        }
        return productsToAdd;
    }

    @Override
    public Set<Product> getProductsByPrice(Integer priceFrom, String priceTo) {
        Set<Product> productsToAdd = new HashSet<>();
        productsToAdd.addAll(productDao.getByIntervalPrice(priceFrom, priceTo));
        return productsToAdd;
    }
}