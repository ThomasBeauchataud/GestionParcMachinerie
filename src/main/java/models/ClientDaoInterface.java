package models;

import beans.entities.Client;
import models.common.CommonDaoInterface;

public interface ClientDaoInterface extends CommonDaoInterface<Client> {

    /**
     * Return a Client from the database identified by his email
     * @param email String
     * @return Client
     */
    Client getByEmail(String email);

}
