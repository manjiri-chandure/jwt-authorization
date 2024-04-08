package com.jwtauth.schoolauthorization.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDto {
    private Integer id;
    private String fullName;
    private String gender;
    private Integer age;
    private String phoneNumber;
    private String email;
    private List<SubjectDto> subjectDtoList;
}
