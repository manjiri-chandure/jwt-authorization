package com.jwtauth.schoolauthorization.repository;

import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.config.school.datasource.SchoolDatabaseConnMapper;

import java.util.List;

@SchoolDatabaseConnMapper
public interface SubjectRepository {
  List<SubjectEntity> findAllSubjects();

  void addSubject(SubjectEntity subjectEntity);
}
