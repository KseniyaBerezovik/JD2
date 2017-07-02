package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.QOrder;
import by.itacademy.entity.userEntity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public List<Order> getByUser(User owner) {
        QOrder order = new QOrder("order");
        JPAQuery<Order> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        List<Order> orders = query.select(order)
                .from(order)
                .where(order.owner.eq(owner))
                .fetch();
        return orders;
    }

    @Override
    public List<Order> getByDate(LocalDateTime from, LocalDateTime to) {
        QOrder order = new QOrder("order");
        JPAQuery<Order> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        List<Order> orders = query.select(order)
                .from(order)
                .where(order.detail.dateOfReceipt.between(from, to))
                .fetch();

        return orders;
    }
}
