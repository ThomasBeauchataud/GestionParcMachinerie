package beans;

import beans.common.NavigationController;
import beans.entities.Machine;
import managers.CatalogManagerInterface;
import managers.MachineManagerInterface;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class MachineCreation extends Machine implements Serializable {

    @Inject
    private MachineManagerInterface machineManager;
    @Inject
    private NavigationController navigationController;
    @Inject
    private CatalogManagerInterface catalogManager;

    public String execute() {
        machineManager.createMachine(this);
        catalogManager.loadMachineCatalog();
        this.ResetMachine();
        return navigationController.goToMachines();
    }

    private void ResetMachine()
    {
        this.setId(0);
        this.setModel("");
        this.setRentPrice(0);
    }
}
