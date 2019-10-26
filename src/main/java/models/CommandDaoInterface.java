package models;

import beans.entities.Command;
import models.common.CommonDaoInterface;

import java.util.List;

public interface CommandDaoInterface extends CommonDaoInterface<Command> {

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

    /**
     * Return Commands from the database identified by their Machine id
     * @param id int
     * @return List<Command>
     */
    List<Command> getByMachineId(int id);

}
