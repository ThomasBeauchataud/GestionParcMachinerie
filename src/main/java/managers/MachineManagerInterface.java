package managers;

import beans.entities.Machine;

import java.util.List;

public interface MachineManagerInterface {

    /**
     *  Delete a machine in the database
     * @param machine Machine
     */
    void deleteMachine(Machine machine);

    /**
     * Edit a machine in the database
     * @param machine Machine
     */
    void editMachine(Machine machine);

    /**
     * Create a machine in the database
     * @param machine Machine
     */
    void createMachine(Machine machine);

    /**
     * Return a Machine from the database identified by his id
     * @param id int
     * @return Machine
     */
    Machine findMachineById(int id);

    /**
     * Return all Machines from the database
     * @return List<Machine>
     */
    List<Machine> findAllMachines();

}
