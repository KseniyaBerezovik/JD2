package by.itacademy.service;

import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService, BaseService<User> {
    User getByLogin(String  login);
}
