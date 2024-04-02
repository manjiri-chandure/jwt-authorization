package com.jwtauth.schoolauthorization.management.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MessOwnerCreationDto {
  @NotBlank(message = "Full name can't be blank")
  private String fullName;
  @Max(60)
  private Integer age;
  @NotNull(message = "gender can't be null")
  private String gender;
  @NotNull
  @Pattern(regexp = "^\\d{10}$", message = "Ivalid number, enter 10 digit only")
  private String contactNumber;
  @NotNull
  @Positive
  private Integer messId;
}
