package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.userEntity.QUser;
import by.itacademy.entity.userEntity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getByLoginAndPassword(String login, String password) {
        QUser qUser = new QUser("user");
        JPAQuery<User> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        User user = query.select(qUser)
                .from(qUser)
                .where(qUser.login.eq(login).and(qUser.password.eq(password)))
                .fetchOne();
        return user;
    }
}
