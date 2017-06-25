package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Detail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class CharacteristicDaoTest extends BaseDaoTest<Characteristic> {

    @Autowired
    private CharacteristicDao characteristicDao;

    @Autowired
    private DetailDao detailDao;

    @Override
    protected BaseDao<Characteristic> getDao() {
        return characteristicDao;
    }

    @Override
    protected Characteristic getModel() {
        return new Characteristic();
    }

    @Test
    public void testGetByDetailAndIntervalValues(){
        getDataImporter().importData();
        Detail detail =detailDao.getByName("Год выпуска");

        List<Characteristic> characteristics =
                characteristicDao.getByDetailAndIntervalValues(detail, "2015", "2017");

        assertThat(characteristics, hasSize(2));
    }

    @Test
    public void testGetByDetailAndValue() {
        getDataImporter().importData();
        Detail detail = detailDao.getByName("Год выпуска");
        List<Characteristic> characteristics =
                characteristicDao.getByDetailAndValue(detail, "2017");

        assertThat(characteristics, hasSize(1));
    }
}