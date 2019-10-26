package beans.views;

import beans.entities.Bill;
import beans.entities.Client;
import beans.entities.Command;
import beans.entities.internal.CommandBill;
import managers.BillManagerInterface;
import managers.ClientManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ClientView implements Serializable {

    @Inject
    private ClientManagerInterface clientManager;
    @Inject
    private BillManagerInterface billManager;

    private Client client;
    private List<CommandBill> commandBillList;

    @PostConstruct
    public void init() {
        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        client = clientManager.findClientById(id);
        commandBillList = new ArrayList<>();
        for(Bill bill : billManager.findBillsByClient(client)) {
            for(Command command : bill.getCommandList()) {
                commandBillList.add(new CommandBill(bill, command));
            }
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CommandBill> getCommandBillList() {
        return commandBillList;
    }

    public void setCommandBillList(List<CommandBill> commandBillList) {
        this.commandBillList = commandBillList;
    }

}
