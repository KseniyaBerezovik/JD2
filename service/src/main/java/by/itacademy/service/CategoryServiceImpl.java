package by.itacademy.service;

import by.itacademy.dao.CategoryDao;
import by.itacademy.entity.productEntity.Category;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
