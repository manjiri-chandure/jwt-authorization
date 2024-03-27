package com.jwtauth.schoolauthorization.Repository;
import com.jwtauth.schoolauthorization.Entity.StudentEntity;
import com.jwtauth.schoolauthorization.Entity.SubjectEntity;
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
}
