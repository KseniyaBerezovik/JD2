package by.itacademy.service;

import by.itacademy.dao.ProfileDao;
import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.Profile;
import by.itacademy.entity.userEntity.User;
import by.itacademy.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl extends BaseServiceImpl<Profile> implements ProfileService {

    @Autowired
    private ProfileDao profileDao;

    @Override
    public Profile getByUser(User user) {
        return profileDao.getByUser(user);
    }

    @Override
    protected BaseDao<Profile> getDao() {
        return profileDao;
    }
}
