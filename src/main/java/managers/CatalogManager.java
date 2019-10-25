package managers;

import beans.entities.Client;
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
    @Inject
    private ClientManagerInterface clientManager;

    private List<MachineCatalog> machineCatalogList;

    @Override
    public List<MachineCatalog> findMachineCatalog() {
        return machineCatalogList;
    }

    @Override
    public void loadMachineCatalog() {
        List<Machine> machines = machineManager.findAllMachines();
        List<Command> commands = commandManager.findFutureCommands();
        List<MachineCatalog> machineCatalogs = new ArrayList<>();
        for(Machine machine : machines) {
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
            machineCatalogs.add(new MachineCatalog(machine.getId(), machine.getModel(), nicheList));
        }
        this.machineCatalogList = machineCatalogs;
    }

    @Override
    public List<Command> generateCommandsWithCatalogs(List<MachineCatalog> machineCatalogList, String clientEmail) {
        List<Command> commandList = new ArrayList<>();
        Client client = clientManager.findClientByEmail(clientEmail);
        for(MachineCatalog machineCatalog : machineCatalogList) {
            commandList.add(new Command(machineCatalog, client, machineCatalog.getSelectNiche().getFrom(), machineCatalog.getSelectNiche().getTo()));
        }
        return commandList;
    }
}
