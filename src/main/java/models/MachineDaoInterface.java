package models;

import beans.entities.Machine;
import models.common.CommonDaoInterface;

public interface MachineDaoInterface extends CommonDaoInterface<Machine> {

    /**
     * Return all Commands from the database
     * @return Command[]
     */
    Machine[] getAll();

}
