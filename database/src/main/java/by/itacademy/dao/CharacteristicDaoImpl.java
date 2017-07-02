package by.itacademy.dao;

import by.itacademy.dao.common.BaseDaoImpl;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.QCharacteristic;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CharacteristicDaoImpl extends BaseDaoImpl<Characteristic> implements CharacteristicDao {

    @Override
    public List<Characteristic> getByDetailAndValue(Detail detail, String value) {
        QCharacteristic characteristic = new QCharacteristic("characteristic");
        JPAQuery<Characteristic> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        List<Characteristic> characteristics = query.select(characteristic)
                .from(characteristic)
                .where(characteristic.detail.eq(detail)
                        .and(characteristic.value.eq(value)))
                .fetchResults()
                .getResults();

        return characteristics;
    }

    @Override
    public List<Characteristic> getByDetailAndIntervalValues(Detail detail, String from, String to) {
        QCharacteristic characteristic = new QCharacteristic("characteristic");
        JPAQuery<Characteristic> query = new JPAQuery<>(getSessionFactory().getCurrentSession());

        List<Characteristic> characteristics = query.select(characteristic)
                .from(characteristic)
                .where(characteristic.detail.eq(detail)
                        .and(characteristic.value.between(from, to)))
                .fetchResults()
                .getResults();
        return characteristics;
    }
}