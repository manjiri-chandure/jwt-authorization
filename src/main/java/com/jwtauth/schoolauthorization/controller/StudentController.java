package com.jwtauth.schoolauthorization.controller;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  StudentService studentService;

  @GetMapping()
  @RolesAllowed({"ROLE_TEACHER", "ROLE_OFFICEADMIN"})
  public ResponseEntity<List<StudentDtoForList>> getStudents() {
    List<StudentDtoForList> studentDtoList = this.studentService.getAllStudent();
    return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}/subjects")
  @RolesAllowed({"ROLE_STUDENT", "ROLE_TEACHER", "ROLE_OFFICEADMIN"})
  public ResponseEntity<StudentDtoForSubject> getStudentWithSubjectList(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal Jwt jwt)
  {
    StudentDtoForSubject studentDtoForSubject = this.studentService.getStudentById(id, jwt);
    return new ResponseEntity<>(studentDtoForSubject, HttpStatus.OK);
  }

  @PostMapping()
  @RolesAllowed({"ROLE_OFFICEADMIN"})
  public ResponseEntity<String> postStudent(@Valid @RequestBody StudentCreationDto studentCreationDto) {
    StudentDto studentDto = this.studentService.postStudent(studentCreationDto);
    String ans = "";
    if(studentDto != null){
        ans += "Student created with Id "+studentDto.getId();
    }
    else{
      ans += "Student Not Created";
    }
    return new ResponseEntity<>(ans, HttpStatus.CREATED);
  }

  @PostMapping("/{id}/subjects")
  @RolesAllowed({"ROLE_TEACHER", "ROLE_OFFICEADMIN"})
  public ResponseEntity<StudentDto> assignSubjectToStudent(@PathVariable(name = "id") Integer id,
                                                            @RequestBody List<SubjectDto> subjectDtoList)
    {
    StudentDto studentDto = this.studentService.assignSubjectsToStudent(id, subjectDtoList);
    return new ResponseEntity<>(studentDto, HttpStatus.OK);
  }

}
