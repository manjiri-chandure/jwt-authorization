package com.jwtauth.schoolauthorization.mapstruct;
import com.jwtauth.schoolauthorization.dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.dto.SubjectDto;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TeacherMapper.class)
public interface SubjectMapper {
    @Mapping(source = "subjectEntity.id", target = "id")
    @Mapping(source = "subjectEntity.name", target = "name")
    SubjectDto toDto(SubjectEntity subjectEntity);

    List<SubjectDto> toDtoList(List<SubjectEntity> subjectEntityList);

    @Mapping(source = "subjectDto.id", target = "id")
    @Mapping(source = "subjectDto.name", target = "name")
    SubjectEntity toEntity(SubjectDto subjectDto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "teacherId", target = "teacherEntity.id")
    SubjectEntity toSubjectEntity(SubjectCreationDto subjectCreationDto);

    List<SubjectEntity> toEntityList(List<SubjectDto> subjectDtoList);
}

