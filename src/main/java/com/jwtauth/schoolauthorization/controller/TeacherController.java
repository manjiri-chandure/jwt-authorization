package com.jwtauth.schoolauthorization.controller;
import com.jwtauth.schoolauthorization.dto.TeacherCreationDto;
import com.jwtauth.schoolauthorization.dto.TeacherDto;
import com.jwtauth.schoolauthorization.dto.TeacherDtoForList;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.service.TeacherService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
public class TeacherController {

  @Autowired
  TeacherService teacherService;

  @GetMapping()
  @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_TEACHER')")
  public List<TeacherDtoForList> getTeachers(@RequestParam(name = "minAge", required = false) Integer minAge,
                                                             @RequestParam(name = "maxAge", required = false) Integer maxAge,
                                                             @RequestParam(name = "gender", required = false) String gender,
                                                             @RequestParam(name = "subject", required = false) String subject) {

    return this.teacherService.getTeachers(minAge, maxAge, gender, subject);
  }

  @GetMapping("/{id}/subjects")
  @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN') or hasRole('ROLE_TEACHER') and #id == authentication.token.claims['UserId']")
  public TeacherDto getTeacherWithSubjectList(@PathVariable(name = "id") Long id){
    String inputTeacherId = id.toString();
    Integer tid = Integer.parseInt(inputTeacherId);
    return this.teacherService.getTeacherById(tid);
  }


  @PostMapping()
  @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
  public TeacherDto postTeacher(@Valid @RequestBody TeacherCreationDto teacherCreationDto) {
   return this.teacherService.insertTeacher(teacherCreationDto);
  }
}
