package managers;

import beans.entities.Machine;

import java.util.List;

public interface MachineManagerInterface {

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

    /**
     * Filter a Machine list to get a catalog
     * @param machines List<Machine>
     * @return List<Machine>
     */
    List<Machine> filterMachinesForCatalog(List<Machine> machines);

}
