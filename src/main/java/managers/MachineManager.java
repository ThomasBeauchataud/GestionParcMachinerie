package managers;

import beans.entities.Machine;
import models.MachineDaoInterface;

import javax.inject.Inject;

public class MachineManager implements MachineManagerInterface {

    @Inject
    private MachineDaoInterface machineDao;

    @Override
    public Machine findMachineById(int id) {
        return null;
    }

}
