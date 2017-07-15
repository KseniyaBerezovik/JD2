package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderContentDaoImpl extends BaseDaoImpl<OrderContent> implements OrderContentDao {
    @Override
    public List<OrderContent> getByOrder(Order order) {
        List<OrderContent> contents = getSessionFactory().getCurrentSession()
                .createQuery("select oc from OrderContent  oc where oc.order.id=:id", OrderContent.class)
                .setParameter("id", order.getId())
                .getResultList();
        return contents;
    }
}
