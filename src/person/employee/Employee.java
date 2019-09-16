package person.employee;

import person.Person;
import authentication.access.Access;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends Person {

    @Column(name = "user")
    @OneToOne
    @JoinTable(name = "user", joinColumns = {@JoinColumn(name = "id")})
    private Access userAccess;

    public Access getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(Access userAccess) {
        this.userAccess = userAccess;
    }
}
