package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import by.itacademy.entity.productEntity.QCharacteristic;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

public class CharacteristicDao extends BaseDao<Characteristic> {

    private CharacteristicDao() {
        super(Characteristic.class);
    }

    private static CharacteristicDao INSTANCE;

    public static CharacteristicDao getInstance() {
        if(INSTANCE == null) {
            synchronized (CharacteristicDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new CharacteristicDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Characteristic> getByDetailAndValue(Session session, Detail detail, String value) {
        QCharacteristic characteristic = new QCharacteristic("characteristic");
        JPAQuery<Characteristic> query = new JPAQuery<>(session);

        List<Characteristic> characteristics = query.select(characteristic)
                .from(characteristic)
                .where(characteristic.detail.eq(detail)
                        .and(characteristic.value.eq(value)))
                .fetchResults()
                .getResults();

        return characteristics;
    }

    public List<Characteristic> getByDetailAndIntervalValues(Session session, Detail detail, String from, String to) {
        QCharacteristic characteristic = new QCharacteristic("characteristic");
        JPAQuery<Characteristic> query = new JPAQuery<>(session);

        List<Characteristic> characteristics = query.select(characteristic)
                .from(characteristic)
                .where(characteristic.detail.eq(detail)
                        .and(characteristic.value.between(from, to)))
                .fetchResults()
                .getResults();
        return characteristics;
    }
}