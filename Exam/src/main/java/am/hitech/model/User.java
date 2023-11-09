package am.hitech.model;

import am.hitech.model.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String status;

    private int salary;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private Roles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Check> check;
}
