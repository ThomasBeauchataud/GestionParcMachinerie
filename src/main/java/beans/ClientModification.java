package beans;

import beans.common.NavigationController;
import beans.entities.Client;
import managers.ClientManagerInterface;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ClientModification extends Client implements Serializable {

    @Inject
    private ClientManagerInterface clientManager;
    @Inject
    private NavigationController navigationController;

    public String Edit() {
        clientManager.editClient(this);
        return navigationController.goToClients();
    }

    public String Delete() {
        clientManager.deleteClient(this);
        return navigationController.goToClients();
    }

    public String edit(int id) {
        try {
            this.Clone(clientManager.findClientById(id));
            return navigationController.goToClientModification();
        } catch (Exception e) {
            return navigationController.goToClients();
        }
    }

    private void Clone(Client client)
    {
        this.setId(client.getId());
        this.setName(client.getName());
        this.setSurname(client.getSurname());
        this.setEmail(client.getEmail());
    }

}
