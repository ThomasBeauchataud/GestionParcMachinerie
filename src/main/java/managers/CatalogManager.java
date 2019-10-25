package managers;

import beans.entities.Command;
import beans.entities.Machine;
import beans.entities.internal.MachineCatalog;
import beans.entities.internal.Niche;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Default
public class CatalogManager implements CatalogManagerInterface {

    @Inject
    private MachineManagerInterface machineManager;
    @Inject
    private CommandManagerInterface commandManager;

    private List<MachineCatalog> machineCatalogList;
    private List<Machine> machines;
    private List<Command> commands;

    @Override
    public List<MachineCatalog> findMachineCatalog() {
        if(machineCatalogList == null || machines == null || commands == null) {
            this.loadMachineCatalog();
        }
        return machineCatalogList;
    }

    @Override
    public void loadMachineCatalog() {
        machines = machineManager.findAllMachines();
        commands = commandManager.findFutureCommands();
        List<MachineCatalog> machineCatalogs = new ArrayList<>();
        for(Machine machine : machines) {
            boolean existingModel = false;
            for(MachineCatalog machineCatalog : machineCatalogs) {
                if(machine.getModel().equals(machineCatalog.getModel())) {
                    existingModel = true;
                    break;
                }
            }
            List<Date> dateList = new ArrayList<>();
            List<Niche> nicheList = new ArrayList<>();
            Niche firstNiche = new Niche();
            firstNiche.setFrom(new Date());
            nicheList.add(firstNiche);
            for(Command command : commands) {
                if(command.getMachine().getId() == machine.getId()) {
                    dateList.add(command.getFrom());
                    dateList.add(command.getTo());
                }
            }
            for(int i = 0 ; i < dateList.size() ; i += 2) {
                Niche niche = nicheList.get(nicheList.size()-1);
                if(dateList.get(i).after(niche.getFrom())) {
                    niche.setFrom(dateList.get(i));
                    continue;
                }
                niche.setTo(dateList.get(i));
                nicheList.set(nicheList.size()-1, niche);
                Niche newNiche = new Niche();
                newNiche.setFrom(dateList.get(i+1));
                nicheList.add(newNiche);
            }
            Niche niche = nicheList.get(nicheList.size()-1);
            niche.setTo(null);
            nicheList.set(nicheList.size()-1, niche);
            if(existingModel) {
                for(int i = 0 ; i < machineCatalogs.size() ; i++) {
                    MachineCatalog machineCatalog = machineCatalogs.get(i);
                    if(machine.getModel().equals(machineCatalog.getModel())) {
                        machineCatalog.addSlots(nicheList);
                        machineCatalogs.set(i, machineCatalog);
                        break;
                    }
                }
            } else {
                machineCatalogs.add(new MachineCatalog(machine.getModel(), nicheList));
            }
        }
        this.machineCatalogList = machineCatalogs;
    }
}
