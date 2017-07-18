package by.itacademy.service;

import by.itacademy.dao.OrderDetailDao;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.orderEntity.OrderDetail;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    protected BaseDao<OrderDetail> getDao() {
        return orderDetailDao;
    }
}
