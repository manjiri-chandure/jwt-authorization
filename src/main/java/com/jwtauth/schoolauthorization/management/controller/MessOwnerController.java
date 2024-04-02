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

import java.util.List;

@RestController
@RequestMapping("/mess_owner")
@RolesAllowed("MESS_OWNER")
public class MessOwnerController {
  @Autowired
  MessOwnerService messOwnerService;

  @GetMapping("")
  public ResponseEntity<List<MessOwnerDto>> getMessOwners() {
    List<MessOwnerDto> messOwners = this.messOwnerService.getMessOwners();
    return ResponseEntity.ok(messOwners);
  }

  @PostMapping("")
  public ResponseEntity<MessOwnerDto> createMessOwner(@Valid @RequestBody MessOwnerCreationDto messOwnerCreationDto)
    throws ResourceNotFoundException {
    MessOwnerDto messOwnerDto = this.messOwnerService.createMessOwner(messOwnerCreationDto);
    return new ResponseEntity<>(messOwnerDto, HttpStatus.CREATED);
  }
}
