package by.itacademy.service.common;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.otherEntity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{

    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public Long save(T entity) {
        return baseDao.save(entity);
    }

    @Override
    public T getByID(Long id) {
        return baseDao.getByID(id);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }
}
