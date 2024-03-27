package com.jwtauth.schoolauthorization.Repository;

import com.jwtauth.schoolauthorization.Entity.SubjectEntity;
import com.jwtauth.schoolauthorization.config.SchoolDataSource.SchoolDatabaseConnMapper;

import java.util.List;
@SchoolDatabaseConnMapper
public interface SubjectRepository {
    List<SubjectEntity> findAllSubjects();

    void addSubject(SubjectEntity subjectEntity);
}
