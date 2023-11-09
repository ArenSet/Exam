package am.hitech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "assignee_id")
    private Integer assigneeId;


    private String summary;

    @Column(name = "`dec`")
    private String description;

    @Column(name = "`status`")
    private String status;

    private String priority;

    private Integer progress;


    @Column(name = "created_date")
    private Long createdDate;


    @Column(name = "due_date")
    private Long dueDate;


    @Column(name = "estimate_time")
    private Integer estimateTime;


    @Column(name = "project_id")
    private Integer projectId;
}
