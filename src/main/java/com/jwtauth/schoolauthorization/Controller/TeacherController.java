package com.jwtauth.schoolauthorization.Controller;
import com.jwtauth.schoolauthorization.dto.TeacherCreationDto;
import com.jwtauth.schoolauthorization.dto.TeacherDto;
import com.jwtauth.schoolauthorization.dto.TeacherDtoForList;
import com.jwtauth.schoolauthorization.service.TeacherService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RolesAllowed({"ROLE_TEACHER", "ROLE_OFFICEADMIN"})
public class TeacherController {

  @Autowired
  TeacherService teacherService;

  @GetMapping()
  public ResponseEntity<List<TeacherDtoForList>> getTeachers(@RequestParam(name = "minAge", required = false) Integer minAge,
                                                             @RequestParam(name = "maxAge", required = false) Integer maxAge,
                                                             @RequestParam(name = "gender", required = false) String gender,
                                                             @RequestParam(name = "subject", required = false) String subject) {

    List<TeacherDtoForList> teacherDtoList = this.teacherService.getTeachers(minAge, maxAge, gender, subject);
    return new ResponseEntity<>(teacherDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}/subjects")
  public ResponseEntity<TeacherDto> getTeacherWithSubjectList(@PathVariable(name = "id") Integer id){
    TeacherDto teacherDto = this.teacherService.getTeacherById(id);
    return new ResponseEntity<>(teacherDto, HttpStatus.OK);
  }


  @PostMapping()
  public ResponseEntity<TeacherDto> postTeacher(@Valid @RequestBody TeacherCreationDto teacherCreationDto) {
    TeacherDto teacherDto = this.teacherService.insertTeacher(teacherCreationDto);
    return new ResponseEntity<>(teacherDto, HttpStatus.CREATED);
  }
}
