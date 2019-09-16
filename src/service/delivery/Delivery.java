package service.delivery;

import common.location.Address;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    @OneToOne(targetEntity = Address.class)
    @JoinTable(name = "address", joinColumns = {@JoinColumn(name = "id")})
    private Address address;

    @Column(name = "date")
    private Date date;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
