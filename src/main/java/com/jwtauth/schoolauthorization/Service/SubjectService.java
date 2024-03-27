package com.jwtauth.schoolauthorization.Service;
import com.jwtauth.schoolauthorization.Dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.Dto.SubjectDto;
import com.jwtauth.schoolauthorization.Entity.SubjectEntity;
import com.jwtauth.schoolauthorization.MapStruct.SubjectMapper;
import com.jwtauth.schoolauthorization.Repository.SubjectRepository;
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
