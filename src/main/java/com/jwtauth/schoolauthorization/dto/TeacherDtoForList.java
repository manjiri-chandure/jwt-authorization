package com.jwtauth.schoolauthorization.dto;

import lombok.Data;

@Data
public class TeacherDtoForList {
    private Integer id;
    private String fullName;
    private String gender;
    private Integer age;
    private String phoneNumber;
    private String email;
}
