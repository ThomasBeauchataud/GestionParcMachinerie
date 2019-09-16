package service.command;

import inventory.accessory.Accessory;
import inventory.machine.Machine;
import person.client.Client;
import service.delivery.Delivery;

import java.sql.Timestamp;
import java.util.ArrayList;

public abstract class Command {

    private int id;
    private Client client;
    private ArrayList<Machine> machines;
    private ArrayList<Accessory> accessories;
    private Delivery delivery;
    private Timestamp timestamp;
    private CommandStatus status;

    public Command() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public void addMachines(ArrayList<Machine> machines) { this.machines.addAll(machines); }

    public ArrayList<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(ArrayList<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

}
