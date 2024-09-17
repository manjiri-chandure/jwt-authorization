package com.jwtauth.schoolauthorization.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubjectCreationDto {
    @NotBlank(message = "Subject name can't be blank")
    @NotNull(message = "Subject can not be null")
    private String name;
    @NotNull(message = "Teacher Id can't be null")
    @Positive(message = "teacher id should be positive integer")
    private Integer teacherId;
}
