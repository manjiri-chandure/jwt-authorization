package com.jwtauth.schoolauthorization.management.controller;
import com.jwtauth.schoolauthorization.management.dto.MessCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnersDto;
import com.jwtauth.schoolauthorization.management.service.MessService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping
public class MessController {

    @Autowired
    MessService messService;
    @GetMapping("/mess")
    @RolesAllowed({"ROLE_STUDENT", "ROLE_MESS_OWNER"})
    public List<MessDto> getmess(){
        return this.messService.getAllMess();
    }

    @PostMapping("/mess")
    @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
    public MessDto createMess(@Valid @RequestBody MessCreationDto messCreationDto){
        return this.messService.createMess(messCreationDto);
    }


  @GetMapping("/mess/{mess_id}/owners")
  @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')or hasRole('ROLE_MESS_OWNER') and #mess_id == authentication.token.claims['UserId']")
  public MessOwnersDto getAllOwners(@PathVariable("mess_id") Integer mess_id){
    return this.messService.getAllOwners(mess_id);
    }
}
