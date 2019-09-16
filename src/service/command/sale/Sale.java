package service.command.sale;

import bill.Bill;
import service.command.Command;

import java.sql.Date;

public class Sale extends Command {

    private Bill bill;
    private Date date;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
