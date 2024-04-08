package com.jwtauth.schoolauthorization.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MessCreationDto {
    @NotBlank(message = "mess name can't be blank")
    private String name;
    @NotNull
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String contactNumber;
    @NotBlank(message = "veg or non-veg type mess")
    private String messType;
    @NotBlank(message = "location can't be blank")
    private String location;
}
