package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.QUser;
import by.itacademy.entity.userEntity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

public class UserDao extends BaseDao<User> {
    private UserDao() {
        super(User.class);
    }

    private static UserDao INSTANCE;

    public static UserDao getInstance() {
        if(INSTANCE == null) {
            synchronized (ClientDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public User getByLoginAndPassword(Session session, String login, String password) {
        QUser qUser = new QUser("user");
        JPAQuery<User> query = new JPAQuery<>(session);

        User user = query.select(qUser)
                .from(qUser)
                .where(qUser.login.eq(login).and(qUser.password.eq(password)))
                .fetchOne();
        return user;
    }
}
