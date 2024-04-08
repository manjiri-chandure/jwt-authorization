package com.jwtauth.schoolauthorization.management.dto;

import lombok.Data;

@Data
public class MessOwnerDto{
    private Integer id;
    private String fullName;
    private Integer age;
    private String gender;
    private String contactNumber;
    private Integer messId;
}
