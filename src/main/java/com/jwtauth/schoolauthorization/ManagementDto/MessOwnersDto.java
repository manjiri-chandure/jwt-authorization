package com.jwtauth.schoolauthorization.ManagementDto;

import lombok.Data;

import java.util.List;
@Data
public class MessOwnersDto {
    private Integer id;
    private String name;
    private List<MessOwnerDto> messOwnerDtos;
}
