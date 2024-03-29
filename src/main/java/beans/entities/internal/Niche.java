package beans.entities.internal;

import java.io.Serializable;
import java.util.Date;

public class Niche implements Serializable {

    private Date from;
    private Date to;

    public Niche() { }

    public Niche(Date from, Date to) {
        this.from = from;
        this.to = to;
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
}
