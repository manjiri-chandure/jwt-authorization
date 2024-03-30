package com.jwtauth.schoolauthorization.ManagementService;
import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnerCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.MessOwnerDto;
import com.jwtauth.schoolauthorization.ManagementEntity.MessEntity;
import com.jwtauth.schoolauthorization.ManagementEntity.MessOwnerEntity;
import com.jwtauth.schoolauthorization.ManagementMapStruct.MessOwnerMapper;
import com.jwtauth.schoolauthorization.ManagementRepository.MessOwnerRepository;
import com.jwtauth.schoolauthorization.ManagementRepository.MessRepository;
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
