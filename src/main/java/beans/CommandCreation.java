package beans;

import beans.common.NavigationController;
import beans.entities.Command;
import beans.entities.Machine;
import managers.CommandManagerInterface;
import managers.MachineManagerInterface;

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
    private MachineManagerInterface machineManager;
    @Inject
    private NavigationController navigationController;

    private List<Machine> machineCatalog;
    private List<Machine> machineCatalogFiltered;
    private List<Machine> machineList;
    private List<Machine> basket;
    private int count;
    private String filterValue;

    @PostConstruct
    public void init() {
        machineList = machineManager.findAllMachines();
        machineCatalog = machineManager.filterMachinesForCatalog(machineList);
        machineCatalogFiltered = machineCatalog;
    }

    public List<Machine> getMachineCatalog() {
        return machineCatalog;
    }

    public void setMachineCatalog(List<Machine> machineCatalog) {
        this.machineCatalog = machineCatalog;
    }

    public List<Machine> getMachineCatalogFiltered() {
        return machineCatalogFiltered;
    }

    public void setMachineCatalogFiltered(List<Machine> machineCatalogFiltered) {
        this.machineCatalogFiltered = machineCatalogFiltered;
    }

    public List<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
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
            for(Machine machine : machineCatalog) {
                if(machine.getModel().contains(filterValue)) {
                    machineCatalogFiltered.add(machine);
                }
            }
        }
    }

}
