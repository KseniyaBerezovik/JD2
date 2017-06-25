package by.itacademy.service;

import by.itacademy.entity.userEntity.Client;

public interface ClientService {
    Client getByID(Long id);
    Long save(Client client);
}
