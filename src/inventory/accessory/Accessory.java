package inventory.accessory;

import inventory.Status;

public class Accessory {

    private int id;
    private Status status;
    private AccessoryFamily family;
    private AccessoryModel model;
    private int rentPrice;
    private int salePrice;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AccessoryFamily getFamily() {
        return family;
    }

    public void setFamily(AccessoryFamily family) {
        this.family = family;
    }

    public AccessoryModel getModel() {
        return model;
    }

    public void setModel(AccessoryModel model) {
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
