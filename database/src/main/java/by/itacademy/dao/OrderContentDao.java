package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;

import java.util.List;

public interface OrderContentDao extends BaseDao<OrderContent> {
    List<OrderContent> getByOrder(Order order);
}
