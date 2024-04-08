package com.jwtauth.schoolauthorization.management.service;
import com.jwtauth.schoolauthorization.management.dto.HostelCreationDto;
import com.jwtauth.schoolauthorization.management.dto.HostelDto;
import com.jwtauth.schoolauthorization.management.entity.HostelEntity;
import com.jwtauth.schoolauthorization.management.mapstruct.HostelMapper;
import com.jwtauth.schoolauthorization.management.repository.HostelRepository;
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
