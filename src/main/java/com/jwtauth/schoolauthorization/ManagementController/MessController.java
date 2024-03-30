package com.jwtauth.schoolauthorization.ManagementController;

import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.ManagementDto.MessCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnersDto;
import com.jwtauth.schoolauthorization.ManagementService.MessService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MessController {

    @Autowired
    MessService messService;
    @GetMapping("/mess")
    public ResponseEntity<List<MessDto>> getmess(){
        List<MessDto> mess = this.messService.getAllMess();
        return ResponseEntity.ok(mess);
    }

    @PostMapping("/mess")
    public ResponseEntity<MessDto> createMess(@Valid @RequestBody MessCreationDto messCreationDto){
        MessDto messDto = this.messService.createMess(messCreationDto);
        return new ResponseEntity<>(messDto, HttpStatus.CREATED);
    }

    @GetMapping("/mess/{mess_id}/owners")
    public ResponseEntity<MessOwnersDto> getAllOwners(@PathVariable ("mess_id") Integer mess_id) throws ResourceNotFoundException {
        MessOwnersDto messOwnersDto = this.messService.getAllOwners(mess_id);
        return ResponseEntity.ok(messOwnersDto);
    }
}
