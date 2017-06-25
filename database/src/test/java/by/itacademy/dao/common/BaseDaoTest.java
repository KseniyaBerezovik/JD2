package by.itacademy.dao.common;

import by.itacademy.entity.otherEntity.BaseEntity;
import by.itacademy.util.DataImporter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public abstract class BaseDaoTest<T extends BaseEntity> {

    protected abstract BaseDao<T> getDao();
    protected abstract T getModel();

    @Autowired
    private DataImporter dataImporter;

    protected DataImporter getDataImporter() {
        return dataImporter;
    }

    @Test
    public void getByIdTest() {
        T model = getModel();
        getDao().save(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void saveTest() {
        T model = getModel();
        getDao().save(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void deleteTest() {
        T model = getModel();
        getDao().save(model);
        Long id = model.getId();
        getDao().delete(model);
        T result = getDao().getByID(id);
        assertNull(result);
    }

    @Test
    public void updateTest() {
        T model = getModel();
        getDao().update(model);
        T result = getDao().getByID(model.getId());
        assertNotNull(result);
    }

    @Test
    public void findAllTest() {
        List<T> list = getDao().findAll();
        assertNotNull(list);
    }
}