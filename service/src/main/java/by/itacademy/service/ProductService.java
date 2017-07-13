package by.itacademy.service;

import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseService;

import java.util.List;
import java.util.Set;


public interface ProductService  extends BaseService<Product> {
    List<Product> getByCategoryName(String name);
    List<Product> getByNumberPageAndCount(Integer numberPage, Category category);
    Integer getNextImageNumber();
    List<Integer> getPages(Category category);
    List<Product> getByCharacteristics(List<Characteristic> characteristics);
    Set<Product> getProductsByYears(List<Integer> years, Integer yearFrom, Integer yearTo);
    Set<Product> getProductsByPrice(Integer priceFrom, String priceTo);
}
