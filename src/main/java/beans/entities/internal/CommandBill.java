package beans.entities.internal;

import beans.entities.Bill;
import beans.entities.Command;

import java.io.Serializable;

public class CommandBill implements Serializable {

    private Bill bill;
    private Command command;

    public CommandBill() { }

    public CommandBill(Bill bill, Command command) {
        this.bill = bill;
        this.command = command;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
