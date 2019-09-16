package person.employee.trainer;

import person.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "trainer")
public class Trainer extends Employee {

    @Column(name = "trainingLevel")
    @Enumerated(EnumType.STRING)
    public TrainingLevel trainingLevel;
}
