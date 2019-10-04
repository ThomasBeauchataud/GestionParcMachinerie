package beans;

import beans.enums.MachineFamily;
import beans.enums.MachineModel;
import beans.enums.Status;

public class Machine {

    private int id;
    private Status status;
    private MachineFamily family;
    private MachineModel model;
    private int rentPrice;
    private int salePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MachineFamily getFamily() {
        return family;
    }

    public void setFamily(MachineFamily family) {
        this.family = family;
    }

    public MachineModel getModel() {
        return model;
    }

    public void setModel(MachineModel model) {
        this.model = model;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}
