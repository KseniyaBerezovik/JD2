package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.QOrder;
import by.itacademy.entity.userEntity.Client;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDao extends BaseDao<Order> {

    private OrderDao() {
        super(Order.class);
    }

    private static OrderDao INSTANCE;

    public static OrderDao getInstance() {
        if(INSTANCE == null) {
            synchronized (OrderDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new OrderDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Order> getByClient(Session session, Client owner) {
        QOrder order = new QOrder("order");
        JPAQuery<Order> query = new JPAQuery<>(session);

        List<Order> orders = query.select(order)
                .from(order)
                .where(order.owner.eq(owner))
                .fetch();
        return orders;
    }

    public List<Order> getByDate(Session session, LocalDateTime from, LocalDateTime to) {
        QOrder order = new QOrder("order");
        JPAQuery<Order> query = new JPAQuery<>(session);

        List<Order> orders = query.select(order)
                .from(order)
                .where(order.detail.dateOfReceipt.between(from, to))
                .fetch();

        return orders;
    }
}
