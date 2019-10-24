package managers;

import beans.entities.Machine;

public interface MachineManagerInterface {

    /**
     * Return a Machine from the database identified by his id
     * @param id int
     * @return Machine
     */
    Machine findMachineById(int id);

}
