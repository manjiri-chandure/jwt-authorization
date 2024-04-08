package com.jwtauth.schoolauthorization.management.service;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.management.dto.MessCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnersDto;
import com.jwtauth.schoolauthorization.management.entity.MessEntity;
import com.jwtauth.schoolauthorization.management.entity.MessOwnerEntity;
import com.jwtauth.schoolauthorization.management.mapstruct.MessMapper;
import com.jwtauth.schoolauthorization.management.repository.MessOwnerRepository;
import com.jwtauth.schoolauthorization.management.repository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MessService {
    @Autowired
    MessRepository messRepository;
    @Autowired
    MessMapper messMapper;

    @Autowired
    MessOwnerRepository messOwnerRepository;


    public List<MessDto> getAllMess(){
        List<MessEntity> messEntities = this.messRepository.findAllMess();
        return this.messMapper.toDtoList(messEntities);
    }


    public MessDto createMess(@RequestBody MessCreationDto messCreationDto){
        MessEntity messEntity = this.messMapper.toEntity(messCreationDto);
        this.messRepository.add(messEntity);
        return this.messMapper.toDto(messEntity);
    }


    public MessOwnersDto getAllOwners(Integer mess_id) throws ResourceNotFoundException {
        MessEntity messEntity = this.messRepository.findMessByID(mess_id);
        if(messEntity == null){
            throw new ResourceNotFoundException("mess with id "+mess_id+" not found");
        }
        List<MessOwnerEntity> messOwnerEntities = this.messOwnerRepository.findAllMessOwnersByMessId(mess_id);
        return this.messMapper.toMessOwnersDto(messEntity,messOwnerEntities);
    }
}
