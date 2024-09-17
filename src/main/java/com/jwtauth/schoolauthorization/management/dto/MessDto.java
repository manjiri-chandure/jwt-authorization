package com.jwtauth.schoolauthorization.management.dto;

import lombok.Data;

@Data
public class MessDto {
    private Integer id;
    private String name;
    private String contactNumber;
    private String messType;
    private String location;
}
