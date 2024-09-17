package com.jwtauth.schoolauthorization.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class SubjectEntity {
    private Integer id;
    private String name;
    @JsonBackReference
    private TeacherEntity teacherEntity;
}
