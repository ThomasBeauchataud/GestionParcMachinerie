package beans;

import beans.common.NavigationController;
import beans.entities.Machine;
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

    public String execute() {
        machineManager.createMachine(this);
        return navigationController.goToMachines();
    }

}
