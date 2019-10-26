package models;

import beans.entities.Command;
import models.common.CommonDaoInterface;

import java.util.List;

public interface CommandDaoInterface extends CommonDaoInterface<Command> {

    /**
     * Return all Commands from the database
     * @return Command[]
     */
    Command[] getAll();

    /**
     * Return all future Commands from the database
     * @return List<Command>
     */
    List<Command> getFuture();

    /**
     * Return the last index of the table
     * @return int
     */
    int getLastIndex();

}
