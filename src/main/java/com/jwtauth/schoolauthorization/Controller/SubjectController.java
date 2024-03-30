package com.jwtauth.schoolauthorization.Controller;

import com.jwtauth.schoolauthorization.Dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.Dto.SubjectDto;
import com.jwtauth.schoolauthorization.Service.SubjectService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RolesAllowed({"STUDENT", "TEACHER", "OFFICEADMIN"})
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    @GetMapping()
    public ResponseEntity<List<SubjectDto>> getSubjects(){
        List<SubjectDto> subjectDtoList = this.subjectService.getSubjects();
        return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
   }

    @PostMapping()
    public ResponseEntity<SubjectDto> postSubject(@Valid @RequestBody SubjectCreationDto subjectCreationDto){
         SubjectDto subjectDto = this.subjectService.insertSubject(subjectCreationDto);
         return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
   }

}
