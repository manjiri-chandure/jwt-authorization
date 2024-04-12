package com.jwtauth.schoolauthorization.controller;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')")
  public List<StudentDtoForList> getStudents() {
    return this.studentService.getAllStudent();
  }

  @GetMapping("/{id}/subjects")
  @PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_OFFICE_ADMIN') or (hasRole('ROLE_STUDENT') and #id == authentication.token.claims['UserId'])")
  public StudentDtoForSubject getStudentWithSubjectList(@PathVariable(name = "id") Long id)
  {
    String s = id.toString();
    System.out.println(id);
    Integer sid = Integer.parseInt(s);
    return this.studentService.getStudentById(sid);

  }

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
  public StudentDto postStudent(@Valid @RequestBody StudentCreationDto studentCreationDto) {
    return this.studentService.postStudent(studentCreationDto);
  }

  @PostMapping("/{id}/subjects")
  @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_TEACHER')")
  public StudentDto assignSubjectToStudent(@Valid @RequestBody List<SubjectDto> subjectDtoList, @PathVariable(name = "id") Integer id)
    {
    return this.studentService.assignSubjectsToStudent(id, subjectDtoList);

  }
}
