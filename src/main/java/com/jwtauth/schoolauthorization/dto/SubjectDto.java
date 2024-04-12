package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubjectDto {
    @Positive(message = "subject id should be positive integer")
    @NotNull(message = "subject id can't be null")
    private Integer id;

    @NotNull(message = "subject name can't be null")
    private String name;
}
