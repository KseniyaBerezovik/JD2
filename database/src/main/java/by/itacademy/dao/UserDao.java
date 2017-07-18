package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    User getByLogin(String login);
}
