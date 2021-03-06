package by.victory.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="people")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column (name="name", nullable=false)
    private String name;

    @Column (name="patronymic", nullable=false)
    private String patronymic;

    @Column (name="surname", nullable=false)
    private String surname;

    @Column (name="birth_date", nullable=false)
    private Date birthDate;

    @Column (name="passport_number", nullable=false, unique = true)
    private String passportNumber;

    @Column (name="email", unique = true)
    private String email;

    @ElementCollection
    private Set<String> cityList=new HashSet<>();
}