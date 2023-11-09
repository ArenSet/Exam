package am.hitech.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String language;

    private String version;

    @Column(name = "team_lead_id")
    private Integer teamLeadId;

    private Integer progress;
}
