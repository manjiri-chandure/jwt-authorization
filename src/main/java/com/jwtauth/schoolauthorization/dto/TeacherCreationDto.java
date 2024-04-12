package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TeacherCreationDto {
    @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname")
    private String fullName;
    @NotNull(message = "gender can't be null")
    @Pattern(regexp="male|female|Male|Female", message = "gender should be male/Male or female/Female")
    private String gender;
    @Min(value = 21, message = "age must be greater or equals to 21 & less than 61")
    @Max(value = 60, message = "age must be less or equals to 60 & greater than 20")
    private Integer age;
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String phoneNumber;
    @Email(message = "email field should be in email format")
    private String email;
}
