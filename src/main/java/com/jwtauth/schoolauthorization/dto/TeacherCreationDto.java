package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TeacherCreationDto {
    @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname")
    private String fullName;
    @NotNull(message = "gender can't be null")
    private String gender;
    @Min(21)
    private Integer age;
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String phoneNumber;
    @Email(message = "should be in email format")
    private String email;
}
