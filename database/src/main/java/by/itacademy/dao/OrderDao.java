package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    List<Order> getByUser(User owner);
}
