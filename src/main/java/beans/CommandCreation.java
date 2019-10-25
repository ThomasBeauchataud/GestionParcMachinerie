package beans;

import beans.common.NavigationController;
import beans.entities.Command;
import beans.entities.internal.MachineCatalog;
import managers.CatalogManagerInterface;
import managers.CommandManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CommandCreation extends Command implements Serializable {

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
    private int count;

    @PostConstruct
    public void init() {
        machineCatalog = catalogManager.findMachineCatalog();
        machineCatalogFiltered = machineCatalog;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(MachineCatalog machineCatalog) {
        machineCatalog.setFrom(getFrom());
        machineCatalog.setTo(getTo());
        machineCatalog.setNumber(count);
        basket.add(machineCatalog);
    }

    public String execute() {
        return navigationController.goToCommands();
    }

    public void filter() {
        machineCatalogFiltered = new ArrayList<>();
        if(filterValue != null && !filterValue.equals("")) {
            for(MachineCatalog machine : machineCatalog) {
                if(machine.getModel().contains(filterValue)) {
                    machineCatalogFiltered.add(machine);
                }
            }
        } else {
            machineCatalogFiltered = machineCatalog;
        }
    }

    public int maxValue(MachineCatalog machineCatalog) {
        return machineCatalog.getCount(getFrom(), getTo());
    }

}
