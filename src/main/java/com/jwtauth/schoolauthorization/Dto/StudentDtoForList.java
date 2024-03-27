package com.jwtauth.schoolauthorization.Dto;

import lombok.Data;

@Data
public class StudentDtoForList{
    private Integer id;
    private String fullName;
    private String gender;
    private Integer age;
}
