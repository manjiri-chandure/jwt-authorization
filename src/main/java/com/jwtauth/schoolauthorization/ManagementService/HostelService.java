package com.jwtauth.schoolauthorization.ManagementService;
import com.jwtauth.schoolauthorization.ManagementDto.HostelCreationDto;
import com.jwtauth.schoolauthorization.ManagementDto.HostelDto;
import com.jwtauth.schoolauthorization.ManagementEntity.HostelEntity;
import com.jwtauth.schoolauthorization.ManagementMapStruct.HostelMapper;
import com.jwtauth.schoolauthorization.ManagementRepository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class HostelService{

    @Autowired
    HostelRepository hostelRepository;

    @Autowired
    HostelMapper hostelMapper;
    public List<HostelDto> getAllHostels() {
        List<HostelEntity> hostelEntities = this.hostelRepository.findAllHostels();
        return this.hostelMapper.toDtoList(hostelEntities);
    }

    public HostelDto createHostel(HostelCreationDto hostelCreationDto) {
        HostelEntity hostelEntity = this.hostelMapper.toEntity(hostelCreationDto);
        this.hostelRepository.add(hostelEntity);
        return this.hostelMapper.toDto(hostelEntity);
    }
}
