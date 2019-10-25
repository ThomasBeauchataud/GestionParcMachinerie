package beans.entities.internal;

import beans.entities.Machine;

import java.io.Serializable;
import java.util.List;

public class MachineCatalog extends Machine implements Serializable {

    private List<Niche> slots;
    private Niche selectNiche;

    public MachineCatalog(int id, String model, List<Niche> slots) {
        this.setId(id);
        this.setModel(model);
        this.slots = slots;
    }

    public List<Niche> getSlots() {
        return slots;
    }

    public void setSlots(List<Niche> slots) {
        this.slots = slots;
    }

    public void addSlots(List<Niche> slots) {
        this.slots.addAll(slots);
    }

    public Niche getSelectNiche() {
        return selectNiche;
    }

    public void setSelectNiche(Niche selectNiche) {
        this.selectNiche = selectNiche;
    }

}
