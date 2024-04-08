package com.jwtauth.schoolauthorization.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDtoForSubject {
    private Integer id;
    private String fullName;
    private List<SubjectDto> subjectDtoList;
}

