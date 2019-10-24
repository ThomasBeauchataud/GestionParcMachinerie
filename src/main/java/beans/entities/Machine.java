package beans.entities;

import java.io.Serializable;

public class Machine implements Serializable {

    private int id;
    private String family;
    private String model;
    private int rentPrice;
    private int businessDiscount;
    private String status;

    public Machine() { }

    public Machine(int id) {
        this.id = id;
    }

    public Machine(int id, String family, String model, int rentPrice, int businessDiscount, String status) {
        this.id = id;
        this.family = family;
        this.model = model;
        this.rentPrice = rentPrice;
        this.businessDiscount = businessDiscount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getBusinessDiscount() {return businessDiscount;}

    public void setBusinessDiscount(int businessDiscount) {this.businessDiscount = businessDiscount;}

}
