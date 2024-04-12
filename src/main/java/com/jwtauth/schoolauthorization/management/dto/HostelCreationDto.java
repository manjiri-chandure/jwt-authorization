package com.jwtauth.schoolauthorization.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HostelCreationDto {
    @NotBlank(message = "hostel name can't be blank")
    private String name;
    @NotBlank(message = "hostelType should be boyHostel Or girlHostel")
    @Pattern(regexp="boysHostel|girlsHostel|BoysHostel|GirlsHostel", message = "Hostel type can be boysHostel/girlsHostel/BoysHostel/GirlsHostel")
    private String hostelType;
    @NotBlank(message = "location can't be blank")
    private String location;
}
