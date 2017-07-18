package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByLogin(String login) {
        List<User> users = getSessionFactory().getCurrentSession()
                .createQuery("select u from User u where u.login=:login", User.class)
                .setParameter("login", login)
                .getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }
}