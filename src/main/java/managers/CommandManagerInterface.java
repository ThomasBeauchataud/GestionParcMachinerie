package managers;

import beans.entities.Command;

public interface CommandManagerInterface {

    /**
     * Return all Commands from the database
     * @return Command[]
     */
    Command[] findAllCommands();

}
