package beans.views;

import beans.entities.Command;
import managers.CommandManagerInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class MachineView implements Serializable {

    @Inject
    private CommandManagerInterface commandManager;

    private List<Command> commandList;

    @PostConstruct
    public void init() {
        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        commandList = commandManager.findByMachineId(id);
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

}
