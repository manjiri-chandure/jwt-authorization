package com.jwtauth.schoolauthorization.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class TeacherEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String phoneNumber;
    private String email;
    @JsonManagedReference
    private List<SubjectEntity> subjectEntities;
}
