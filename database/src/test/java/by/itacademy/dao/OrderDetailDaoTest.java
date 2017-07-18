package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.orderEntity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailDaoTest extends BaseDaoTest<OrderDetail> {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    protected BaseDao<OrderDetail> getDao() {
        return orderDetailDao;
    }

    @Override
    protected OrderDetail getModel() {
        return new OrderDetail();
    }
}
