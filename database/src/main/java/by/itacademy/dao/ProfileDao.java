package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.Profile;
import by.itacademy.entity.userEntity.User;

public interface ProfileDao extends BaseDao<Profile> {
    Profile getByUser(User user);
}
