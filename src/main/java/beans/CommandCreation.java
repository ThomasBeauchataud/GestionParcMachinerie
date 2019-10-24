package beans;

import beans.common.NavigationController;
import beans.entities.Command;
import managers.CommandManagerInterface;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CommandCreation extends Command implements Serializable {

    @Inject
    private CommandManagerInterface commandManager;
    @Inject
    private NavigationController navigationController;

    public String execute() {
        commandManager.createCommand(this);
        return navigationController.goToCommands();
    }


}
