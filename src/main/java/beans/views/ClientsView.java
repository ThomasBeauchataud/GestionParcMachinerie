package beans.views;

import beans.entities.Client;
import managers.ClientManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ClientsView implements Serializable {

    @Inject
    private ClientManagerInterface clientManager;
    private List<Client> clientList;

    @PostConstruct
    public void init() {
        this.clientList = clientManager.findAllClients();
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

}
