package by.itacademy.service;

import by.itacademy.entity.userEntity.Profile;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;

public interface ProfileService extends BaseService<Profile> {
    Profile getByUser(User user);
}
