package by.itacademy.service;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseService;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getByUser(User owner);
    Order createOrder(User user);
}