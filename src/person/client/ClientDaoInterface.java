package person.client;

import authentication.access.client.ClientAccess;

public interface ClientDaoInterface {

    public void save(Client client);

    public Client find(String name);

    public Client find(ClientAccess clientAccess);

}
