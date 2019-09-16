package service.command.rent;

import bill.Bill;
import service.command.Command;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent extends Command {

    @Column(name = "startRent")
    private Date startRent;

    @Column(name = "endRent")
    private Date endRent;

    @Column(name = "bills")
    @OneToMany(targetEntity = Bill.class)
    @JoinTable(name = "bill", joinColumns = {@JoinColumn(name = "id")})
    private ArrayList<Bill> bills;

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(Date startRent) {
        this.startRent = startRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

}
