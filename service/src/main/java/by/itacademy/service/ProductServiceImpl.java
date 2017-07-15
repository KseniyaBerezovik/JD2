package by.itacademy.service;

import by.itacademy.dao.ProductDao;
import by.itacademy.dto.FilterDto;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.common.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService  {

    private static Logger LOGGER = Logger.getLogger(ProductService.class);

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
    public Integer getNextImageNumber() {
        return productDao.getNextImageNumber();
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

        if(isPriceParamsPresent(filterDto)) {
            addPriceParams(detailIdValueMap, filterDto.getPriceFrom(), filterDto.getPriceTo());
        }

        List<Product> resultProducts = productDao.testCriteria(detailIdValueMap)
                .stream()
                .map(i -> productDao.getByID(i))
                .collect(Collectors.toList());

        return resultProducts;
    }

    private void addPriceParams(Map<Long, List<String>> detailIdValueMap, String priceFrom, String priceTo) {
        if(priceFrom != null && priceTo != null) {
            detailIdValueMap.put(4L, Arrays.asList(priceFrom, priceTo));
        }
        if(priceFrom != null && priceTo == null) {
            detailIdValueMap.put(4L, Arrays.asList("FROM:" + priceFrom));
        }
        if(priceFrom == null && priceTo != null) {
            detailIdValueMap.put(4L, Arrays.asList("TO:" + priceTo));
        }
    }

    private boolean isPriceParamsPresent(FilterDto filterDto) {
        return filterDto.getPriceFrom() != null || filterDto.getPriceTo() != null;
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