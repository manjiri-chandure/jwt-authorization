package com.jwtauth.schoolauthorization.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class SubjectEntity {
    private Integer id;
    private String name;
    @JsonBackReference
    private TeacherEntity teacherEntity;
}
