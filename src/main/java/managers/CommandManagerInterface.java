package managers;

import beans.entities.Command;

import java.util.List;

public interface CommandManagerInterface {

    /**
     * Return all Commands from the database
     * @return Command[]
     */
    Command[] findAllCommands();

    /**
     * Create a new Command and store it in the database
     * @param command Command
     */
    void createCommand(Command command);

    /**
     * Return all future commands
     * @return List<Command>
     */
    List<Command> findFutureCommands();

}
