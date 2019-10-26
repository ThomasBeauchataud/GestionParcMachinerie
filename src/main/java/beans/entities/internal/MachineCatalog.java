package beans.entities.internal;

import beans.entities.Machine;

import java.io.Serializable;
import java.util.List;

public class MachineCatalog extends Machine implements Serializable {

    private List<Niche> slots;
    private Niche selectNiche;

    public MachineCatalog(Machine machine, List<Niche> slots) {
        this.setId(machine.getId());
        this.setModel(machine.getModel());
        this.setRentPrice(machine.getRentPrice());
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
