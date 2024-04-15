package com.jwtauth.schoolauthorization.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MessCreationDto {
    @NotNull(message = "mess name can't be null")
    @NotBlank(message = "mess name can't be blank")
    private String name;
    @NotNull(message = "contact number can't be null")
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String contactNumber;
    @NotBlank(message = "veg or non-veg type mess")
    @Pattern(regexp="veg|Veg|non-veg|Non-Veg", message = "mess type can be veg/Veg/non-veg/Non-Veg")
    private String messType;
    @NotBlank(message = "location can't be blank")
    private String location;
}
