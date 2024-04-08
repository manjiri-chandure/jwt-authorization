package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentCreationDto {
    @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname")
    private String fullName;
    @NotNull(message = "gender can't be null")
    private String gender;
    @Min(10)
    @Max(25)
    private Integer age;
}
