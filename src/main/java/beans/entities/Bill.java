package beans.entities;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {

    private int id;
    private Client client;
    private List<Command> commandList;
    private int value;
    private boolean paid = false;

    public Bill() { }

    public Bill(int id, Client client, List<Command> commandList, int value, boolean paid) {
        this.id = id;
        this.client = client;
        this.commandList = commandList;
        this.value = value;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public void addCommand(Command command) {
        this.commandList.add(command);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
