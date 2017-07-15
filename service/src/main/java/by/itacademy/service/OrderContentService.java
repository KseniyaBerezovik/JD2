package by.itacademy.service;

import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.service.common.BaseService;

import java.util.List;

public interface OrderContentService extends BaseService<OrderContent> {
    List<OrderContent> getByOrder(Order order);
}
