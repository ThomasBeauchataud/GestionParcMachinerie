package managers;

import beans.entities.Machine;
import models.MachineDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Default
public class MachineManager implements MachineManagerInterface {

    @Inject
    private MachineDaoInterface machineDao;

    @Override
    public void createMachine(Machine machine) {
        machineDao.insert(machine);
    }

    @Override
    public Machine findMachineById(int id) {
        return machineDao.getById(id);
    }

    @Override
    public List<Machine> findAllMachines() {
        return Arrays.asList(machineDao.getAll());
    }

    @Override
    public List<Machine> filterMachinesForCatalog(List<Machine> machines) {
        return machines;
    }

}
