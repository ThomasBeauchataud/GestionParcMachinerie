package models;

import beans.entities.Command;
import models.common.CommonDaoInterface;

public interface CommandDaoInterface extends CommonDaoInterface<Command> {

    /**
     * Return all Commands from the database
     * @return Command[]
     */
    Command[] getAll();

}
