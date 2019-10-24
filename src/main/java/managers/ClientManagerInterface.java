package managers;

import beans.entities.Client;

public interface ClientManagerInterface {

    void createClient(Client client);

    /**
     * Return a Client from the database identified by his id
     * @param id int
     * @return Client
     */
    Client findClientById(int id);

}
