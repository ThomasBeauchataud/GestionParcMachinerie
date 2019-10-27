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
public class MachineModification extends Machine implements Serializable {

    @Inject
    private MachineManagerInterface machineManager;
    @Inject
    private NavigationController navigationController;
    @Inject
    private CatalogManagerInterface catalogManager;

    public String Edit() {
        machineManager.editMachine(this);
        return navigationController.goToMachines();
    }

    public String Delete() {
        machineManager.deleteMachine(this);
        catalogManager.loadMachineCatalog();
        return navigationController.goToMachines();
    }

    public String edit(int id) {
        try {
            this.Clone(machineManager.findMachineById(id));
            return navigationController.goToMachineModification();
        } catch (Exception e) {
            return navigationController.goToMachines();
        }
    }

    public void Clone(Machine machine)
    {
        this.setId(machine.getId());
        this.setModel(machine.getModel());
        this.setRentPrice(machine.getRentPrice());
    }

}
