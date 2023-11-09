package am.hitech.model.dto;

import am.hitech.model.enums.Roles;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {

    private int id;
    @NotBlank(message = "first name can't be null")
    private String name;
    @NotBlank(message = "last name can't be null")
    private String surname;
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;
    @Size(min = 3, max = 15, message = "password must be between 3 to 15")
    private String password;
    @NotBlank
    private String status;
    @NotNull
    private int salary;
    @NotNull
    private Roles roles;
}
