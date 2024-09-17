package com.jwtauth.schoolauthorization.management.service;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerDto;
import com.jwtauth.schoolauthorization.management.entity.MessEntity;
import com.jwtauth.schoolauthorization.management.entity.MessOwnerEntity;
import com.jwtauth.schoolauthorization.management.mapstruct.MessOwnerMapper;
import com.jwtauth.schoolauthorization.management.repository.MessOwnerRepository;
import com.jwtauth.schoolauthorization.management.repository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class MessOwnerService {

    @Autowired
    MessOwnerRepository messOwnerRepository;

    @Autowired
    MessRepository messRepository;

    @Autowired
    MessOwnerMapper messOwnerMapper;
    public List<MessOwnerDto> getMessOwners(){
           List<MessOwnerEntity> messOwnerEntities = this.messOwnerRepository.findAllMessOwners();
           return this.messOwnerMapper.toDtoList(messOwnerEntities);
         }


    public MessOwnerDto createMessOwner(@RequestBody MessOwnerCreationDto messOwnerCreationDto)throws ResourceNotFoundException{
             Integer mid = messOwnerCreationDto.getMessId();
             MessEntity messEntity = this.messRepository.findMessByID(mid);
             if(messEntity == null){
                 throw new ResourceNotFoundException("Mess with Id "+mid+" not exists");
             }
             MessOwnerEntity messOwnerEntity = this.messOwnerMapper.toEntity(messOwnerCreationDto);
             this.messOwnerRepository.add(messOwnerEntity);
             return this.messOwnerMapper.toDto(messOwnerEntity);
         }
}
