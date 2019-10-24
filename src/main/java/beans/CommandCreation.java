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
    private List<Command> basket;
    private int count;
    private String filterValue;

    @PostConstruct
    public void init() {
        machineCatalog = catalogManager.findMachineCatalog();
        machineCatalogFiltered = machineCatalog;
    }

    public List<MachineCatalog> getMachineCatalogFiltered() {
        return machineCatalogFiltered;
    }

    public void setMachineCatalogFiltered(List<MachineCatalog> machineCatalogFiltered) {
        this.machineCatalogFiltered = machineCatalogFiltered;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public void add(String model) {

    }

    public String execute() {
        commandManager.createCommand(this);
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

}
