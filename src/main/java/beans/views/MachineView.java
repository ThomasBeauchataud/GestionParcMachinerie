package beans.views;

import beans.entities.Command;
import beans.entities.Machine;
import managers.CommandManagerInterface;
import managers.MachineManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MachineView implements Serializable {

    @Inject
    private CommandManagerInterface commandManager;
    @Inject
    private MachineManagerInterface machineManager;

    private List<Command> commandList;
    private Machine machine;

    @PostConstruct
    public void init() {
        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        commandList = commandManager.filterByDate(commandManager.findByMachineId(id));
        machine = machineManager.findMachineById(id);
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

}
