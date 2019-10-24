package managers;

import beans.entities.Machine;

public interface MachineManagerInterface {

    /**
     * Return a Machine from the database identified by his id
     * @param id int
     * @return Machine
     */
    Machine findMachineById(int id);

    /**
     * Create a machine in the database
     * @param machine Machine
     */
    void createMachine(Machine machine);

    /**
     * Return all Machines from the database
     * @return Machine[]
     */
    Machine[] findAllMachines();


}
