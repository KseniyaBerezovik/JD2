package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    @Override
    public List<Order> getByUser(User owner) {
        List<Order> orders = getSessionFactory().getCurrentSession()
                .createQuery("select o from Order o where o.owner.id=:id", Order.class)
                .setParameter("id", owner.getId())
                .getResultList();
        return orders;
    }

    @Override
    public List<Order> getByDate(LocalDateTime from, LocalDateTime to) {
        List orders = getSessionFactory().getCurrentSession()
                .createQuery("select o from Order o where o.detail.dateOfReceipt >= :start and o.detail.dateOfReceipt <= :to")
                .setParameter("start", from)
                .setParameter("to", to)
                .getResultList();
        return orders;
    }
}
