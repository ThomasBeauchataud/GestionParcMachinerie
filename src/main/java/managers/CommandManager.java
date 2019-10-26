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
    public List<Command> findAllCommands() {
        List<Command> commands = commandDao.getAll();
        List<Command> finalCommandList = new ArrayList<>();
        for(Command command : commands) {
            finalCommandList.add(this.enrichCommand(command));
        }
        return finalCommandList;
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
    public Command findCommandById(int id) {
        Command command = commandDao.getById(id);
        return this.enrichCommand(command);
    }

    @Override
    public List<Command> findByMachineId(int id) {
        List<Command> commands = commandDao.getByMachineId(id);
        List<Command> finalCommandList = new ArrayList<>();
        for(Command command : commands) {
            finalCommandList.add(this.enrichCommand(command));
        }
        return finalCommandList;
    }

    @Override
    public List<Command> filterByDate(List<Command> commands) {
        for(int i = 0 ; i < commands.size() - 1 ; i++) {
            for(int k = 0 ; k < commands.size() - i - 1 ; k++) {
                if(commands.get(i).getFrom().after(commands.get(i+1).getFrom())) {
                    Command command = commands.get(i);
                    commands.set(i, commands.get(i+1));
                    commands.set(i+1, command);
                }
            }
        }
        return commands;
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
