package com.jwtauth.schoolauthorization.Controller;


import com.jwtauth.schoolauthorization.Dto.*;
import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.Service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RolesAllowed("STUDENT")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentDtoForList>> getStudents(){
        List<StudentDtoForList> studentDtoList = this.studentService.getAllStudent();
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<StudentDtoForSubject> getStudentWithSubjectList(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException, ResourceNotFoundException {
        StudentDtoForSubject studentDtoForSubject = this.studentService.getStudentById(id);
        return new ResponseEntity<>(studentDtoForSubject, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<StudentDto> postStudent(@Valid @RequestBody StudentCreationDto studentCreationDto){
        StudentDto studentDto  = this.studentService.postStudent(studentCreationDto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<StudentDto> assignSubjectsToStudent(@PathVariable (name ="id") Integer id,@RequestBody List<SubjectDto> subjectDtoList) throws ResourceNotFoundException
        {
            StudentDto studentDto = this.studentService.assignSubjectsToStudent(id, subjectDtoList);
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
}
