package com.jwtauth.schoolauthorization.Controller;
import com.jwtauth.schoolauthorization.dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.dto.SubjectDto;
import com.jwtauth.schoolauthorization.service.SubjectService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RolesAllowed({"ROLE_STUDENT", "ROLE_TEACHER", "ROLE_OFFICEADMIN"})
public class SubjectController {

  @Autowired
  SubjectService subjectService;

  @GetMapping()
  public ResponseEntity<List<SubjectDto>> getSubjects() {
    List<SubjectDto> subjectDtoList = this.subjectService.getSubjects();
    return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<SubjectDto> postSubject(@Valid @RequestBody SubjectCreationDto subjectCreationDto) {
    SubjectDto subjectDto = this.subjectService.insertSubject(subjectCreationDto);
    return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
  }

}
