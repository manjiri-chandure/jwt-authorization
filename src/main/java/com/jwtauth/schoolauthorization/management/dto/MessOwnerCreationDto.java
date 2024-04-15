package com.jwtauth.schoolauthorization.management.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MessOwnerCreationDto {
    @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname")
    private String fullName;
    @Min(value = 21, message = "age must be greater or equals to 21 & less than 61")
    @Max(value = 60, message = "age must be less or equals to 60 & greater than 20")
    @NotNull(message = "age can't be null")
    private Integer age;
    @NotNull(message = "gender can't be null")
    @Pattern(regexp="male|female|Male|Female", message = "gender should be male/Male or female/Female")
    private String gender;
    @NotNull(message = "contact number can't be null")
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String contactNumber;
    @NotNull(message = "messId can't be null")
    @Positive
    private Integer messId;
}
