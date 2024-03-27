package com.jwtauth.schoolauthorization.Service;

import com.jwtauth.schoolauthorization.Dto.*;
import com.jwtauth.schoolauthorization.Entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jwtauth.schoolauthorization.Entity.SubjectEntity;
import com.jwtauth.schoolauthorization.MapStruct.SubjectMapper;
import com.jwtauth.schoolauthorization.Repository.SubjectRepository;
import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.Repository.StudentRepository;
import com.jwtauth.schoolauthorization.MapStruct.StudentMapper;

import java.util.List;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;

   public List<StudentDtoForList> getAllStudent(){
        List<StudentEntity> studentEntityList =  this.studentRepository.findAllStudents();
        return this.studentMapper.toDtoList(studentEntityList);
   }

    public StudentDtoForSubject getStudentById(Integer id) throws ResourceNotFoundException {
        StudentEntity studentEntity = this.studentRepository.findStudentById(id);
        if(studentEntity == null){
            throw new ResourceNotFoundException("student with id "+id+" not found");
        }
        return this.studentMapper.toDtoForSubject(studentEntity);
   }

    public StudentDto postStudent(StudentCreationDto studentCreationDto){
        StudentEntity studentEntity = this.studentMapper.toEntity(studentCreationDto);
        this.studentRepository.addStudent(studentEntity);
        return this.studentMapper.toDto(studentEntity);
     }

    public StudentDto assignSubjectsToStudent( Integer id, List<SubjectDto> subjectDtoList) throws ResourceNotFoundException{
        StudentEntity studentEntity = this.studentRepository.findStudentById(id);
        if(studentEntity == null){
            throw new ResourceNotFoundException("student not found with id "+id);
        }
        if(subjectDtoList != null){
            List<SubjectEntity> subjectEntities  = this.subjectRepository.findAllSubjects();
            for(SubjectDto subjectDto : subjectDtoList){
                Integer subId = subjectDto.getId();
                int flag = 0;
                for(SubjectEntity subjectEntity : subjectEntities){
                    if(subjectEntity.getId().equals(subId)){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0){
                    throw new ResourceNotFoundException("subject with subject Id "+subId+" not exists");
                }
            }
            List<SubjectEntity> subjectEntityList = this.subjectMapper.toEntityList(subjectDtoList);

            this.studentRepository.assignSubjectsToStudent(id, subjectEntityList);

        }
         return this.studentMapper.toDto(this.studentRepository.findStudentById(id));
    }
}
