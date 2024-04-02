package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubjectCreationDto {
  @NotBlank(message = "Subject name can't be blank")
  private String name;
  @NotNull
  @Positive
  private Integer teacherId;
}
