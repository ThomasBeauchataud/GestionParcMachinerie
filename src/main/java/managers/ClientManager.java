package managers;

import beans.entities.Client;
import models.ClientDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Default
public class ClientManager implements ClientManagerInterface {

    @Inject
    private ClientDaoInterface clientDao;

    @Override
    public void createClient(Client client) { clientDao.insert(client); }

    @Override
    public List<Client> findAllClients() { return clientDao.getAll(); }

    @Override
    public Client findClientById(int id) {
        return clientDao.getById(id);
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientDao.getByEmail(email);
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.deleteById(client.getId());
    }

    @Override
    public void editClient(Client client) { clientDao.update(client); }

}
