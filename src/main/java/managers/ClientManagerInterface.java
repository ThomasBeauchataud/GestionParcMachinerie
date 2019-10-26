package managers;

import beans.entities.Client;

import java.util.List;

public interface ClientManagerInterface {

    void createClient(Client client);

    List<Client> findAllClients();

    /**
     * Return a Client from the database identified by his id
     * @param id int
     * @return Client
     */
    Client findClientById(int id);

    /**
     * Return a Client from the database identified by his email
     * @param email String
     * @return Client
     */
    Client findClientByEmail(String email);

    /**
     *  Delete a client in the database
     * @param client Client
     */
    void deleteClient(Client client);

    /**
     * Edit a client in the database
     * @param client Client
     */
    void editClient(Client client);

}
