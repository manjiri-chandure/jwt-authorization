package com.jwtauth.schoolauthorization.service;
import com.jwtauth.schoolauthorization.KafkaValidationgroup;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.entity.StudentEntity;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.exception.ResourceAlreadyExistsException;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.kafka.producer.MessageProducer;
import com.jwtauth.schoolauthorization.mapstruct.SubjectMapper;
import com.jwtauth.schoolauthorization.mapstruct.StudentMapper;
import com.jwtauth.schoolauthorization.repository.StudentRepository;
import com.jwtauth.schoolauthorization.repository.SubjectRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private Validator validator;

    @Autowired
    private MessageProducer messageProducer;




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


  @KafkaListener(topics = "Students", groupId = "Students", containerFactory = "kafkaListenerContainerFactory")
  @KafkaHandler
  @RetryableTopic(
    backoff = @Backoff(value = 3000L),
    attempts = "12",
    autoCreateTopics = "true",
    include = RuntimeException.class)
    public StudentDto postStudent(StudentCreationDtoByKafka studentCreationDtoByKafka){
      StudentEntity studentEntity = null;
      if(studentCreationDtoByKafka.getFullName().equals("shilpa chandure")){
        throw new RuntimeException("Shilpa is not allowed");
      }
      LogDto logDto = new LogDto();
      logDto.setLid(studentCreationDtoByKafka.getLid());
      try {
        validateStudentCreationDto(studentCreationDtoByKafka);
        StudentCreationDto studentCreationDto1 = this.studentMapper.toStudentCreationDtoFromKafkaDto(studentCreationDtoByKafka);
        studentEntity = this.studentMapper.toEntity(studentCreationDto1);
        this.studentRepository.addStudent(studentEntity);
        logDto.setResponseMessage("OK: Student Created Successfully");
        logDto.setStatusCode(200);
        messageProducer.sendMessageToTopic(logDto);
        System.out.println(logDto);
      }
      catch (ValidationException ex){
        logDto.setResponseMessage("BAD_REQUEST: {'errors':['"+ex.getMessage()+"']}");
        logDto.setStatusCode(400);
        messageProducer.sendMessageToTopic(logDto);
        System.out.println(logDto);
      }
      catch (Exception ignored){
        logDto.setResponseMessage("Internal Server Error");
        logDto.setStatusCode(500);
        messageProducer.sendMessageToTopic(logDto);
        System.out.println(logDto);
      }

      if(studentEntity != null)
        return this.studentMapper.toDto(studentEntity);
      return null;

    }


  private void validateStudentCreationDto(StudentCreationDtoByKafka dto) {
    Set<ConstraintViolation<StudentCreationDtoByKafka>> violations = validator.validate(dto, KafkaValidationgroup.class);
    if (!violations.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<StudentCreationDtoByKafka> violation : violations) {
        sb.append(violation.getMessage()).append(" ");
      }
      throw new ValidationException(sb.toString());
    }
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