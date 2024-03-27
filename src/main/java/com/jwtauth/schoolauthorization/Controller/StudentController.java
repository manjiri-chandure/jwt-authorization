package com.jwtauth.schoolauthorization.Controller;


import com.jwtauth.schoolauthorization.Dto.*;
import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.Service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping()
    @RolesAllowed("ROLE_STUDENT")
    public ResponseEntity<List<StudentDtoForList>> getStudents(){
        List<StudentDtoForList> studentDtoList = this.studentService.getAllStudent();
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    @RolesAllowed("ROLE_STUDENT")
    public ResponseEntity<StudentDtoForSubject> getStudentWithSubjectList(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException, ResourceNotFoundException {
        StudentDtoForSubject studentDtoForSubject = this.studentService.getStudentById(id);
        return new ResponseEntity<>(studentDtoForSubject, HttpStatus.OK);
    }
  @PostMapping()
  @RolesAllowed("ROLE_STUDENT")
    public ResponseEntity<StudentDto> postStudent(@Valid @RequestBody StudentCreationDto studentCreationDto){
        StudentDto studentDto  = this.studentService.postStudent(studentCreationDto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/subjects")
    @RolesAllowed("ROLE_STUDENT")
    public ResponseEntity<StudentDto> assignSubjectsToStudent(@PathVariable (name ="id") Integer id,@RequestBody List<SubjectDto> subjectDtoList) throws ResourceNotFoundException
        {
            StudentDto studentDto = this.studentService.assignSubjectsToStudent(id, subjectDtoList);
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
}
