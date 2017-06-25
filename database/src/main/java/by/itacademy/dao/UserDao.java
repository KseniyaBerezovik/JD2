package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.User;

public interface UserDao extends BaseDao<User> {
    User getByLoginAndPassword(String login, String password);
}
