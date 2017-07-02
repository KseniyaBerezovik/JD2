package by.itacademy.service.common;

import by.itacademy.entity.otherEntity.BaseEntity;
import by.itacademy.entity.userEntity.User;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    Long save(T entity);
    T getByID(Long id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
