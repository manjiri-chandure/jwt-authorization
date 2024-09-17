package com.jwtauth.schoolauthorization.dto;

import lombok.Data;

@Data
public class StudentDtoForList{
    private Integer id;
    private String fullName;
    private String gender;
    private Integer age;
}
