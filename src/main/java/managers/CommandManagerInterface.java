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
     * Return all future commands
     * @return List<Command>
     */
    List<Command> findFutureCommands();

    /**
     * Store a list of Commands in the database
     * @param commands List<Command>
     */
    void createCommands(List<Command> commands);

    /**
     * Return a Command identified by his id
     * @param id int
     * @return Command
     */
    Command findCommandById(int id);

}
