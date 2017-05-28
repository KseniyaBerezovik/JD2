package by.itacademy;


import by.itacademy.dao.ClientDao;
import by.itacademy.entity.Client;

public class ClientService {
    public static Client getClientByID(long id) {
        return ClientDao.getClientByID(id);
    }
}
