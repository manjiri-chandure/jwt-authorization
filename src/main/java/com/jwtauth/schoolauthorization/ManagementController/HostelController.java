package com.jwtauth.schoolauthorization.ManagementController;

import com.jwtauth.schoolauthorization.ManagementDto.HostelCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.HostelDto;
import com.jwtauth.schoolauthorization.ManagementService.HostelService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RolesAllowed({"HOSTEL_ADMIN"})
public class HostelController{

    @Autowired
    HostelService hostelService;
    @GetMapping("/hostels")
    public ResponseEntity<List<HostelDto>> gethostels(){
        List<HostelDto> hostels = this.hostelService.getAllHostels();
        return ResponseEntity.ok(hostels);
    }

    @PostMapping("/hostels")
    public ResponseEntity<HostelDto> createHostel(@Valid @RequestBody HostelCreationDto hostelCreationDto){
        HostelDto hostelDto = this.hostelService.createHostel(hostelCreationDto);
        return new ResponseEntity<>(hostelDto, HttpStatus.CREATED);
    }
}
