package com.jwtauth.schoolauthorization.repository;

import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.config.SchoolDataSource.SchoolDatabaseConnMapper;

import java.util.List;

@SchoolDatabaseConnMapper
public interface SubjectRepository {
  List<SubjectEntity> findAllSubjects();

  void addSubject(SubjectEntity subjectEntity);

	Integer findSubjectByName(String name);
}
