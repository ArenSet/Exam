package am.hitech.model.dto.request;

import lombok.Data;


@Data
public class TaskRequestDto {

    private int id;

    /*@NotBlank(message = "creatorId cant be null")*/
    private Integer creatorId;

    /*@NotBlank(message = "summary cant be null")*/
    private String summary;

    /*@NotBlank(message = "status cant be null")*/
    private String status;

    /*@NotBlank(message = "priority cant be null")*/
    private String priority;

    /*@NotBlank(message = "progress cant be null")*/
    private Integer progress;

    /*@NotBlank(message = "createdDate cant be null")*/
    private Long createdDate;

}