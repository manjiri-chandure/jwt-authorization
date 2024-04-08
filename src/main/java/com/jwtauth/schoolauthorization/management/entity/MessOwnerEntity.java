package com.jwtauth.schoolauthorization.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessOwnerEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String contactNumber;
    private Integer messId;
}


