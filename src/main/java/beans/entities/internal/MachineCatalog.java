package beans.entities.internal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MachineCatalog implements Serializable {

    private String model;
    private List<Niche> slots;
    private int number;
    private Date from;
    private Date to;

    public MachineCatalog() { }

    public MachineCatalog(String model, List<Niche> slots) {
        this.model = model;
        this.slots = slots;
    }

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

    public void addSlots(List<Niche> slots) {
        this.slots.addAll(slots);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getCount(Date from, Date to) {
        if(from == null || to == null) {
            int count = 0;
            for(Niche niche : slots) {
                if(niche.getTo() == null) {
                    count++;
                }
            }
            return count;
        } else {
            int count = 0;
            for(Niche niche : slots) {
                if(niche.getFrom().after(from) && (niche.getTo() == null || niche.getTo().before(to))) {
                    count++;
                }
            }
            return count;
        }
    }

}
