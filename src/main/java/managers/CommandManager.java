package managers;

import beans.entities.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class CommandManager implements CommandManagerInterface {

    @Override
    public Command[] findAllCommands() {
        return new Command[0];
    }

}
