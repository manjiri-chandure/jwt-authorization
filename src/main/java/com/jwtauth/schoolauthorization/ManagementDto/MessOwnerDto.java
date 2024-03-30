package com.jwtauth.schoolauthorization.ManagementDto;

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
