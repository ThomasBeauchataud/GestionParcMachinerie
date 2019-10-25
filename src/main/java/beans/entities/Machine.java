package beans.entities;

import java.io.Serializable;

public class Machine implements Serializable {

    private int id;
    private String model;
    private int rentPrice;

    public Machine() { }

    public Machine(int id) {
        this.id = id;
    }

    public Machine(String model) {
        this.model = model;
    }

    public Machine(int id, String model, int rentPrice) {
        this.id = id;
        this.model = model;
        this.rentPrice = rentPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
