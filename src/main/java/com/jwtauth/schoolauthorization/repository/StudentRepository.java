package com.jwtauth.schoolauthorization.repository;
import com.jwtauth.schoolauthorization.entity.StudentEntity;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.config.SchoolDataSource.SchoolDatabaseConnMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@SchoolDatabaseConnMapper
public interface StudentRepository {
    List<StudentEntity> findAllStudents();
    //StudentEntity getStudentById(Integer id);

    StudentEntity findStudentById(@Param("id")Integer id);

    void addStudent(StudentEntity studentEntity);

    void assignSubjectsToStudent(@Param("id")Integer id, @Param("subjectEntities")List<SubjectEntity> subjectEntities);

	Integer findStudentSubjectId(@Param("studId")Integer studId, @Param("subId")Integer subId);
}
