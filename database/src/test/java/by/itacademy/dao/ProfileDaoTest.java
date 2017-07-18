package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.dao.common.BaseDaoTest;
import by.itacademy.entity.userEntity.Profile;
import by.itacademy.entity.userEntity.User;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfileDaoTest extends BaseDaoTest<Profile> {

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void getByUser() throws Exception {
        getDataImporter().importData();
        User user = userDao.getByLogin("mivan");
        Profile profile = profileDao.getByUser(user);
        assertThat(profile.getTelephone(), is("120"));
    }

    @Override
    protected BaseDao<Profile> getDao() {
        return profileDao;
    }

    @Override
    protected Profile getModel() {
        return new Profile();
    }
}