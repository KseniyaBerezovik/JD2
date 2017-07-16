package by.itacademy.service;

import by.itacademy.dto.FilterDto;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProductService  extends BaseService<Product> {
    List<Product> getByCategoryName(String name, int pageNumber);
    Integer getTotalPage(Category category);
    Integer getNextImageNumber();
    List<Product> getByFilter(FilterDto filterDto);
}
