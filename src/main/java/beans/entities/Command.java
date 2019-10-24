package beans.entities;

import java.io.Serializable;
import java.util.Date;

public class Command implements Serializable {

    private int id;
    private Machine machine;
    private Client client;
    private Date from;
    private Date to;

    public Command() { }

    public Command(int id, Machine machine, Client client, Date from, Date to) {
        this.id = id;
        this.machine = machine;
        this.client = client;
        this.from = from;
        this.to = to;
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
}
