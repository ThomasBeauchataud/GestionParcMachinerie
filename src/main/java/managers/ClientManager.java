package managers;

import beans.entities.Client;
import models.ClientDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Default
public class ClientManager implements ClientManagerInterface {

    @Inject
    private ClientDaoInterface clientDao;

    @Override
    public void createClient(Client client) {

    }

    @Override
    public Client findClientById(int id) {
        return clientDao.getById(id);
    }

}
