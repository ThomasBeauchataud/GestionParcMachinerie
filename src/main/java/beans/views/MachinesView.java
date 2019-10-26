package beans.views;

import beans.entities.Machine;
import managers.MachineManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class MachinesView implements Serializable {

    @Inject
    private MachineManagerInterface machineManager;
    private List<Machine> machineList;

    @PostConstruct
    public void init() {
        this.machineList = machineManager.findAllMachines();
    }

    public List<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
    }

}
