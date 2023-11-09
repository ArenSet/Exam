package am.hitech.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "months")
public class Months {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int month;

    private int checks;

    @Column(name = "user_id")
    private int userId;

}
