package com.jwtauth.schoolauthorization.ManagementController;

import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnerCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnerDto;
import com.jwtauth.schoolauthorization.ManagementService.MessOwnerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess_owner")
public class MessOwnerController {
    @Autowired
    MessOwnerService messOwnerService;
    @GetMapping("")
    public ResponseEntity<List<MessOwnerDto>> getMessOwners(){
        List<MessOwnerDto> messOwners = this.messOwnerService.getMessOwners();
        return ResponseEntity.ok(messOwners);
    }

    @PostMapping("")
    public ResponseEntity<MessOwnerDto> createMessOwner(@Valid @RequestBody MessOwnerCreationDto messOwnerCreationDto) throws ResourceNotFoundException {
        MessOwnerDto messOwnerDto = this.messOwnerService.createMessOwner(messOwnerCreationDto);
        return new ResponseEntity<>(messOwnerDto, HttpStatus.CREATED);
    }
}
