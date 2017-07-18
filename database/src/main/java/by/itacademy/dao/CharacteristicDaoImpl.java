package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.Product;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;
import java.util.Map;

@Repository
public class CharacteristicDaoImpl extends BaseDaoImpl<Characteristic> implements CharacteristicDao {
    @Override
    public List<Characteristic> getByProduct(Product product) {
        List<Characteristic> characteristics = getSessionFactory().getCurrentSession()
                .createQuery("select c from Characteristic c where c.product.id=:id", Characteristic.class)
                .setParameter("id", product.getId())
                .getResultList();
        return characteristics;
    }
}