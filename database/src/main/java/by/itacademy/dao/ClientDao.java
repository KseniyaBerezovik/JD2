package by.itacademy.dao;

import by.itacademy.dao.common.BaseDao;
import by.itacademy.entity.userEntity.Client;

public class ClientDao extends BaseDao<Client> {

    private ClientDao() {
        super(Client.class);
    }

    private static ClientDao INSTANCE;

    public static ClientDao getInstance() {
        if(INSTANCE == null) {
            synchronized (ClientDao.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }
}