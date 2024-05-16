package com.jwtauth.schoolauthorization.service;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.entity.StudentEntity;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.exception.ResourceAlreadyExistsException;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.exception.UnauthorizedException;
import com.jwtauth.schoolauthorization.mapstruct.SubjectMapper;
import com.jwtauth.schoolauthorization.mapstruct.StudentMapper;
import com.jwtauth.schoolauthorization.repository.StudentRepository;
import com.jwtauth.schoolauthorization.repository.SubjectRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;



    public List<StudentDtoForList> getAllStudent() {
        List<StudentEntity> studentEntityList = this.studentRepository.findAllStudents();
        return this.studentMapper.toDtoList(studentEntityList);
    }



  public StudentDtoForSubject getStudentById(Integer id){

    StudentEntity studentEntity = this.studentRepository.findStudentById(id);
    if (studentEntity == null) {
            throw new ResourceNotFoundException("student with id " + id + " not found");
    }
        return this.studentMapper.toDtoForSubject(studentEntity);
    }

    @KafkaListener(topics = "students", groupId = "student", containerFactory = "kafkaListenerContainerFactory")
    public StudentDto postStudent(StudentCreationDto studentCreationDto) {
        StudentEntity studentEntity = this.studentMapper.toEntity(studentCreationDto);
        System.out.println(studentEntity);
        this.studentRepository.addStudent(studentEntity);
        return this.studentMapper.toDto(studentEntity);
    }

    public StudentDto assignSubjectsToStudent(Integer id, List<SubjectDto> subjectDtoList) {
        StudentEntity studentEntity = this.studentRepository.findStudentById(id);
        if (studentEntity == null) {
            throw new ResourceNotFoundException("student not found with id " + id);
        }
        if (subjectDtoList != null) {
            List<SubjectEntity> subjectEntities = this.subjectRepository.findAllSubjects();
            for (SubjectDto subjectDto : subjectDtoList) {
                Integer subId = subjectDto.getId();
                int flag = 0;
                for (SubjectEntity subjectEntity : subjectEntities) {
                    if (subjectEntity.getId().equals(subId)) {
                        flag = 1;
                        Integer sid = this.studentRepository.findStudentSubjectId(id, subId);
                        if(sid != null)
                            throw new ResourceAlreadyExistsException("this subject with subject id "+subId+" is already assigned to student with studentId "+id);
                        break;
                    }

                }
                if(flag == 0){
                    throw new ResourceNotFoundException("subject with subject id "+subId+" Or name "+subjectDto.getName()+" not exists");
                }

            }
            List<SubjectEntity> subjectEntityList = this.subjectMapper.toEntityList(subjectDtoList);

            this.studentRepository.assignSubjectsToStudent(id, subjectEntityList);

        }
        return this.studentMapper.toDto(this.studentRepository.findStudentById(id));
    }

}