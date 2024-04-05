package com.jwtauth.schoolauthorization.Controller;
import com.jwtauth.schoolauthorization.Service.StudentService;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@RolesAllowed("ROLE_STUDENT")
public class StudentController {

  @Autowired
  StudentService studentService;

  @GetMapping()
  public ResponseEntity<List<StudentDtoForList>> getStudents() {
    List<StudentDtoForList> studentDtoList = this.studentService.getAllStudent();
    return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}/subjects")
  public ResponseEntity<StudentDtoForSubject> getStudentWithSubjectList(@PathVariable(name = "id") Integer id)
    throws ResourceNotFoundException, ResourceNotFoundException {
    StudentDtoForSubject studentDtoForSubject = this.studentService.getStudentById(id);
    return new ResponseEntity<>(studentDtoForSubject, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<StudentDto> postStudent(@Valid @RequestBody StudentCreationDto studentCreationDto) {
    StudentDto studentDto = this.studentService.postStudent(studentCreationDto);
    return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
  }

  @PostMapping("/{id}/subjects")
  public ResponseEntity<StudentDto> assignSubjectToStudent(@PathVariable(name = "id") Integer id,
                                                            @RequestBody List<SubjectDto> subjectDtoList)
    throws ResourceNotFoundException {
    StudentDto studentDto = this.studentService.assignSubjectsToStudent(id, subjectDtoList);
    return new ResponseEntity<>(studentDto, HttpStatus.OK);
  }

  @PostMapping("/assign_subjects")
  public List<StudentDtoForSubject> assignSubjects(@RequestBody SubjectDtoForAssign subjectDtoForAssign)
    throws ResourceNotFoundException{
    List<StudentDtoForSubject> students = this.studentService.assignSubjectsToStudents(subjectDtoForAssign);
    return students;
  }
}
