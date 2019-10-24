package managers;

import beans.entities.Machine;
import models.MachineDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Default
public class MachineManager implements MachineManagerInterface {

    @Inject
    private MachineDaoInterface machineDao;

    @Override
    public Machine findMachineById(int id) {
        return machineDao.getById(id);
    }

    @Override
    public void createMachine(Machine machine) {

    }

    @Override
    public Machine[] findAllMachines() {
        Machine[] machines = machineDao.getAll();
        return machines;
    }
}
