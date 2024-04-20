package com.jwtauth.schoolauthorization.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentCreationDto {
  @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname")
  private String fullName;
  @NotNull(message = "gender can't be null")
  @Pattern(regexp = "male|female|Male|Female", message = "gender should be male/Male or female/Female")
  private String gender;
  @Min(value = 10, message = "age must be greater or equals to 10 & less than 26")
  @Max(value = 25, message = "age must be less or equals to 25 & greater than 9")
  @NotNull(message = "age can't be null")
  private Integer age;
}
