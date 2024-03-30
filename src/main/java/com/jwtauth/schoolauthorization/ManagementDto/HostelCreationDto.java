package com.jwtauth.schoolauthorization.ManagementDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HostelCreationDto {
    @NotBlank(message = "hostel name can't be blank")
    private String name;
    @NotBlank(message = "hostelType should be boyHostel Or girlHostel")
    private String hostelType;
    @NotBlank(message = "location can't be blank")
    private String location;
}
