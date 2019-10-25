package beans;

import beans.common.NavigationController;
import beans.entities.Client;
import beans.entities.Command;
import beans.entities.internal.MachineCatalog;
import beans.entities.internal.Niche;
import managers.BillManagerInterface;
import managers.CatalogManagerInterface;
import managers.ClientManagerInterface;
import managers.CommandManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class CommandCreation implements Serializable {

    @Inject
    private ClientManagerInterface clientManager;
    @Inject
    private BillManagerInterface billManager;
    @Inject
    private CommandManagerInterface commandManager;
    @Inject
    private NavigationController navigationController;
    @Inject
    private CatalogManagerInterface catalogManager;

    private List<MachineCatalog> machineCatalog;
    private List<MachineCatalog> machineCatalogFiltered;
    private List<MachineCatalog> basket;
    private String filterValue;
    private Date from;
    private Date to;
    private String clientEmail;
    private String emailMessage = "This email is not associated to any client";

    @PostConstruct
    public void init() {
        catalogManager.loadMachineCatalog();
        machineCatalog = catalogManager.findMachineCatalog();
        basket = new ArrayList<>();
    }

    public List<MachineCatalog> getMachineCatalogFiltered() {
        return machineCatalogFiltered;
    }

    public void setMachineCatalogFiltered(List<MachineCatalog> machineCatalogFiltered) {
        this.machineCatalogFiltered = machineCatalogFiltered;
    }

    public List<MachineCatalog> getBasket() {
        return basket;
    }

    public void setBasket(List<MachineCatalog> basket) {
        this.basket = basket;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public void add(MachineCatalog machineCatalog) {
        machineCatalog.setSelectNiche(new Niche(from, to));
        basket.add(machineCatalog);
        this.machineCatalog.remove(machineCatalog);
        this.filter();
    }

    public String execute() {
        List<Command> commands = catalogManager.generateCommandsWithCatalogs(basket, clientEmail);
        commandManager.createCommands(commands);
        billManager.generateBill(commands, clientEmail);
        this.init();
        return navigationController.goToCommands();
    }

    public void filter() {
        if(from != null && to != null) {
            machineCatalogFiltered = new ArrayList<>();
            if (filterValue != null && !filterValue.equals("")) {
                for (MachineCatalog machine : machineCatalog) {
                    if (machine.getModel().contains(filterValue)) {
                        machineCatalogFiltered.add(machine);
                    }
                }
            } else {
                machineCatalogFiltered = machineCatalog;
            }
        }
    }

    public void findUserByEmail() {
        Client client = clientManager.findClientByEmail(clientEmail);
        if(client == null) {
            emailMessage = "This email is not associated to any client";
        } else {
            emailMessage = "Client found: " + client.getSurname() + " " + client.getName();
        }
    }

    public String getImageName(MachineCatalog machineCatalog) {
        String[] temp = machineCatalog.getModel().split(" ");
        return temp[0] + "-" + temp[1] + ".png";
    }

}
