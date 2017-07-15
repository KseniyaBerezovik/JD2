package by.itacademy.service;

import by.itacademy.dao.OrderContentDao;
import by.itacademy.entity.orderEntity.Order;
import by.itacademy.entity.orderEntity.OrderContent;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderContentServiceImpl extends BaseServiceImpl<OrderContent> implements OrderContentService {

    @Autowired
    private OrderContentDao orderContentDao;

    @Override
    public List<OrderContent> getByOrder(Order order) {
        return orderContentDao.getByOrder(order);
    }
}