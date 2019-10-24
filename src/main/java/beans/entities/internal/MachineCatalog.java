package beans.entities.internal;

import java.io.Serializable;
import java.util.List;

public class MachineCatalog implements Serializable {

    private String model;
    private List<Niche> slots;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Niche> getSlots() {
        return slots;
    }

    public void setSlots(List<Niche> slots) {
        this.slots = slots;
    }

}
