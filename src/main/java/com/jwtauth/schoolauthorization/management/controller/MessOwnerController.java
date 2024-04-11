package com.jwtauth.schoolauthorization.management.controller;

import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerDto;
import com.jwtauth.schoolauthorization.management.service.MessOwnerService;
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
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/mess_owners")
public class MessOwnerController {

    @Autowired
    MessOwnerService messOwnerService;
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
    public List<MessOwnerDto> getMessOwners(){
        return this.messOwnerService.getMessOwners();
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_MESS_OWNER')")
    public MessOwnerDto createMessOwner(@Valid @RequestBody MessOwnerCreationDto messOwnerCreationDto)
       {
        return this.messOwnerService.createMessOwner(messOwnerCreationDto);
       }

}
