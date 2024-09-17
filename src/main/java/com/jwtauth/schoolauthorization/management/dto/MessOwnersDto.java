package com.jwtauth.schoolauthorization.management.dto;

import lombok.Data;

import java.util.List;
@Data
public class MessOwnersDto {
    private Integer id;
    private String name;
    private List<MessOwnerDto> messOwnerDtos;
}
