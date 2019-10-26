package beans.entities;

import beans.entities.enums.CommandStatus;

import java.io.Serializable;
import java.util.Date;

public class Command implements Serializable {

    private int id;
    private Machine machine;
    private Client client;
    private Date from;
    private Date to;
    private CommandStatus commandStatus = CommandStatus.created;

    public Command() { }

    public Command(int id) {
        this.id = id;
    }

    public Command(Machine machine, Client client, Date from, Date to) {
        this.machine = machine;
        this.client = client;
        this.from = from;
        this.to = to;
    }

    public Command(int id, Machine machine, Client client, Date from, Date to, CommandStatus commandStatus) {
        this.id = id;
        this.machine = machine;
        this.client = client;
        this.from = from;
        this.to = to;
        this.commandStatus = commandStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(CommandStatus commandStatus) {
        this.commandStatus = commandStatus;
    }

}
