package inventory.machine;

import java.util.ArrayList;
import java.util.Date;

public interface MachineDaoInterface {

    /**
     * Return a list of count machine specified by his model during a specific time laps
     * If endDate = null, it returns machines with no future rent planned
     * @param model String
     * @param count String
     * @param startDate Date
     * @param endDate Date
     * @return ArrayList<Machine>
     */
    public ArrayList<Machine> getFreeMachines(String model, int count, Date startDate, Date endDate);

    /**
     * Return all free machines regrouped by model
     * If endDate = null, it returns machines with no future rent planned
     * @param startDate Date
     * @param endDate Date
     * @return ArrayList<MachineCount>
     */
    public ArrayList<Machine> getFreeMachines(Date startDate, Date endDate);

    /**
     * Save a new machine
     * @param machine Machine
     */
    public void saveMachine(Machine machine);

}
