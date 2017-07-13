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
    public List<Characteristic> getByDetailAndValue(Detail detail, String value) {
        List<Characteristic> characteristics = getSessionFactory().getCurrentSession()
                .createQuery("select c from Characteristic c where c.detail.name=:name and c.value=:value", Characteristic.class)
                .setParameter("name", detail.getName())
                .setParameter("value", value)
                .getResultList();
        return characteristics;

    }

    @Override
    public List<Characteristic> getByDetailAndValueList(Detail detail, List<String> values) {
        List<Characteristic> characteristics = getSessionFactory().getCurrentSession()
                .createQuery("select c from Characteristic c where c.detail.name=:name and c.value in (:values)", Characteristic.class)
                .setParameter("name", detail.getName())
                .setParameter("values", values)
                .getResultList();
        return characteristics;
    }

    @Override
    public List<Characteristic> getByDetailAndIntervalValues(Detail detail, String from, String to) {
        List<Characteristic> characteristics = getSessionFactory().getCurrentSession()
                .createQuery("select c from Characteristic c where c.detail.id=:id and " +
                        "c.value >= :from and c.value <= :to", Characteristic.class)
                .setParameter("id", detail.getId())
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
        return characteristics;
    }

    @Override
    public List<Characteristic> getByProduct(Product product) {
        List<Characteristic> characteristics = getSessionFactory().getCurrentSession()
                .createQuery("select c from Characteristic c where c.product.id=:id", Characteristic.class)
                .setParameter("id", product.getId())
                .getResultList();
        return characteristics;
    }
}