package by.itacademy.service;

import by.itacademy.dao.ProductDao;
import by.itacademy.dto.FilterDto;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService  {

    private static Logger LOGGER = Logger.getLogger(ProductService.class);

    private final static int PRODUCT_IN_PAGE = 3;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DetailService detailService;

    @Autowired
    private CharacteristicService characteristicService;

    @Override
    public List<Product> getByCategoryName(String name) {
        LOGGER.info("invocation: getByCategoryName parameters: " + name);
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

    @Override
    public Set<Product> getByDetailAndValueList(Detail detail, List<String> valueList) {
        return characteristicService.getByDetailAndValueList(detail, valueList)
                .stream()
                .map(ch -> ch.getProduct())
                .collect(Collectors.toSet());
    }

    @Override
    public List<Product> getByFilter(FilterDto filterDto) {
        Map<Long, List<String>> detailIdValueMap = new HashMap<>();

        if(isYearsParamPresent(filterDto)) {
            addYearsValues(detailIdValueMap, filterDto.getYears(), filterDto.getYearFrom(), filterDto.getYearTo());
        }

        if(filterDto.getOs() != null) {
            addOsValues(detailIdValueMap, filterDto.getOs());
        }

        List<Product> resultProducts = new ArrayList<>();
        List<Product> productsByFilter = new ArrayList<>();
        List<Product> productsByPrice = new ArrayList<>();

        if(!detailIdValueMap.isEmpty()) {
            productsByFilter = productDao.testCriteria(detailIdValueMap);
        }

        if(isPriceParamsPresent(filterDto)) {
            productsByPrice = productDao.getByIntervalPrice(filterDto.getPriceFrom(), filterDto.getPriceTo());
        }

        if(!productsByFilter.isEmpty() && !productsByPrice.isEmpty()) {
            resultProducts = productsByFilter.stream().filter(productsByPrice::contains).collect(Collectors.toList());
        } else {
            if(!productsByFilter.isEmpty()) {
                resultProducts = productsByFilter;
            } else if(!productsByPrice.isEmpty()) {
                resultProducts = productsByPrice;
            }
        }

        Set<Product> collect = resultProducts.stream().collect(Collectors.toSet());
        return new ArrayList<>(collect);
    }

    private boolean isPriceParamsPresent(FilterDto filterDto) {
        return filterDto.getPriceFrom() != null || !filterDto.getPriceTo().equals("undefined");
    }

    private boolean isYearsParamPresent(FilterDto filterDto) {
        return filterDto.getYears() != null || filterDto.getYearFrom() != null || filterDto.getYearTo() != null;
    }


    private void addYearsValues(Map<Long, List<String>> resultMap, List<Integer> years, Integer yearFrom, Integer yearTo) {
        List<String> resultYears = new ArrayList<>();
        if(years != null) {
            resultYears.addAll(years
                        .stream()
                        .map(year -> String.valueOf(year))
                        .collect(Collectors.toList()));
        }

        if(yearFrom == null && yearTo == null) {
            //do nothing
        } else {
            int start;
            int end;
            if(yearFrom != null && yearTo != null) {
                start = yearFrom;
                end = yearTo;
            } else if(yearFrom == null) {
                start = 0;
                end = yearTo;
            } else {
                start = yearFrom;
                end = LocalDate.now().getYear();
            }
            for(int i = start; i <= end; i++) {
                resultYears.add(String.valueOf(i));
            }
        }

        if(years != null || yearFrom != null || yearTo != null) {
            resultMap.put(1L, resultYears); //TODO:DETAIL ID
        }
    }

    private void addOsValues(Map<Long, List<String>> resultMap, List<String> os){
        resultMap.put(3L, os);
    }
}