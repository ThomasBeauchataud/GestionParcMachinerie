package managers;

import beans.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class ClientManager implements ClientManagerInterface {
    @Override
    public void createClient(Client client) {

    }
}
