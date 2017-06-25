package by.itacademy.service;

import by.itacademy.dao.ClientDao;
import by.itacademy.entity.userEntity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client getByID(Long id) {
        return clientDao.getByID(id);
    }

    @Override
    public Long save(Client client) {
        return clientDao.save(client);
    }
}