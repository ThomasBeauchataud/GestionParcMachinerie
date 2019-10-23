package beans.entities;

import beans.entities.enums.MachineFamily;
import beans.entities.enums.MachineModel;
import beans.entities.enums.Status;

public class Machine {

    private int id;
    private MachineFamily family;
    private MachineModel model;
    private int rentPrice;
    private int salePrice;
    private int businessDiscount;
    private Status status;

    public Machine() { }

    public Machine(int id, String family, String model, int rentPrice, int salePrice, int businessDiscount, String status) {
        this.id = id;
        this.family = MachineFamily.valueOf(family);
        this.model = MachineModel.valueOf(model);
        this.rentPrice = rentPrice;
        this.salePrice = salePrice;
        this.businessDiscount = businessDiscount;
        this.status = Status.valueOf(status);
    }

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

    public int getBusinessDiscount() {
        return businessDiscount;
    }
    public void setBusinessDiscount(int businessDiscount) {
        this.businessDiscount = businessDiscount;
    }
}
