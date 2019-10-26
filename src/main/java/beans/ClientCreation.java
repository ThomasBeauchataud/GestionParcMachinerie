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
public class ClientCreation extends Client implements Serializable {

    @Inject
    private ClientManagerInterface clientManager;
    @Inject
    private NavigationController navigationController;

    public String execute() {
        clientManager.createClient(this);
        this.Reset();
        return navigationController.goToClients();
    }

    private void Reset()
    {
        this.setId(0);
        this.setEmail("");
        this.setName("");
        this.setSurname("");
    }

}
