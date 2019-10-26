package managers;

import beans.entities.Command;
import beans.entities.enums.CommandStatus;
import models.CommandDaoInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Default
public class CommandManager implements CommandManagerInterface {

    @Inject
    private MachineManagerInterface machineManager;
    @Inject
    private ClientManagerInterface clientManager;
    @Inject
    private CommandDaoInterface commandDao;

    @Override
    public Command[] findAllCommands() {
        Command[] commands = commandDao.getAll();
        List<Command> finalCommandList = new ArrayList<>();
        for(Command command : commands) {
            finalCommandList.add(this.enrichCommand(command));
        }
        return finalCommandList.toArray(new Command[0]);
    }

    @Override
    public void createCommands(List<Command> commands) {
        for(Command command : commands) {
            //TODO Here we declare that a command is always paid
            command.setCommandStatus(CommandStatus.paid);
            commandDao.insert(command);
        }
    }

    @Override
    public List<Command> findFutureCommands() {
        List<Command> commands = commandDao.getFuture();
        List<Command> finalCommandList = new ArrayList<>();
        for(Command command : commands) {
            finalCommandList.add(this.enrichCommand(command));
        }
        return finalCommandList;
    }

    private Command enrichCommand(Command command) {
        command.setClient(clientManager.findClientById(command.getClient().getId()));
        command.setMachine(machineManager.findMachineById(command.getMachine().getId()));
        return command;
    }

}
