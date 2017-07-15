package by.itacademy.service;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getByUser(User owner);
    List<Order> getByDate(LocalDateTime from, LocalDateTime to);
    Order createOrder(User user);
}
