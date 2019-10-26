package managers;

import beans.entities.Command;

import java.util.List;

public interface CommandManagerInterface {

    /**
     * Return all Commands from the database
     * @return List<Command>
     */
    List<Command> findAllCommands();

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

    /**
     * Return a Command identified by his Machine id
     * @param id int
     * @return List<Command>
     */
    List<Command> findByMachineId(int id);

    /**
     * Filter Commands by Date
     * @param commandList List<Command>
     * @return List<Command>
     */
    List<Command> filterByDate(List<Command> commandList);

}
