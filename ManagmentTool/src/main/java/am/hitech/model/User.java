package am.hitech.model;


import am.hitech.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    /*@JsonIgnore*/
    private String password;

    @JsonIgnore
    @Column(name = "verification_code")
    private Integer verificationCode;

    @JsonIgnore
    @Column(name = "password_token")
    private Integer passwordToken;

    private String status;

   /* @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;*/

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private Roles role;
}
