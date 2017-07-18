package by.itacademy.common;

import by.itacademy.config.TestServiceConfig;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.otherEntity.BaseEntity;


import by.itacademy.service.common.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public abstract class BaseServiceTest<T extends BaseEntity> {

    protected abstract BaseService<T> getService();
    protected abstract BaseDao<T> getDao();
    protected abstract T getModel();

    @Mock
    private BaseDao<T> dao;

    @InjectMocks
    private BaseService<T> service;

    @Autowired
    public BaseServiceTest(BaseService<T> service) {
        this.service = service;
    }

    @Test
    public void getByIdTest() {
        when(getDao().getByID(1L))
                .thenReturn(getModel());
        T entity = getService().getByID(1L);
        assertThat(entity.getId(), is(1L));
    }


    @Test
    public void findAllTest() {
        when(getDao().findAll())
                .thenReturn(Arrays.asList(getModel()));
        List<T> list = getService().findAll();
        assertThat(list, hasSize(1));
    }
}
