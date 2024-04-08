package com.jwtauth.schoolauthorization.service;
import com.jwtauth.schoolauthorization.dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.dto.SubjectDto;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.mapstruct.SubjectMapper;
import com.jwtauth.schoolauthorization.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;

    public List<SubjectDto> getSubjects(){

        List<SubjectEntity> subjectEntityList = this.subjectRepository.findAllSubjects();
        return this.subjectMapper.toDtoList(subjectEntityList);
    }


    public SubjectDto insertSubject(SubjectCreationDto subjectCreationDto){
       SubjectEntity subjectEntity = this.subjectMapper.toSubjectEntity(subjectCreationDto);
       this.subjectRepository.addSubject(subjectEntity);
       return this.subjectMapper.toDto(subjectEntity);

    }
}
