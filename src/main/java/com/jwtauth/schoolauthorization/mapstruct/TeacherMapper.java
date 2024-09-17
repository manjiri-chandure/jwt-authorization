package com.jwtauth.schoolauthorization.mapstruct;
import com.jwtauth.schoolauthorization.dto.TeacherCreationDto;
import com.jwtauth.schoolauthorization.dto.TeacherDto;
import com.jwtauth.schoolauthorization.dto.TeacherDtoForList;
import com.jwtauth.schoolauthorization.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface TeacherMapper {
    @Mapping(target = "firstName", expression = "java(convertToFirstName(teacherCreationDto))")
    @Mapping(target = "lastName", expression = "java(convertToLastName(teacherCreationDto))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "id", ignore = true)
    TeacherEntity toEntity(TeacherCreationDto teacherCreationDto);

    default String convertToFirstName(TeacherCreationDto teacherCreationDto) {
        if (teacherCreationDto.getFullName() == null) {
            return null;
        }
        return teacherCreationDto.getFullName().substring(0, teacherCreationDto.getFullName().indexOf(" "));
    }

    default String convertToLastName(TeacherCreationDto teacherCreationDto) {
        if (teacherCreationDto.getFullName() == null) {
            return null;
        }
        return teacherCreationDto.getFullName().substring(teacherCreationDto.getFullName().indexOf(" ") + 1);
    }

    @Mapping(target = "fullName", expression = "java(convertToFullName(teacherEntity.getFirstName(), teacherEntity.getLastName()))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "subjectEntities", target = "subjectDtoList")
    TeacherDto toDto(TeacherEntity teacherEntity);

    @Named("toFullName")
    default String convertToFullName(String firstName, String lastname) {
        String fullName = firstName;
        fullName += " ";
        fullName += lastname;
        return fullName;
    }

    @Mapping(target = "fullName", expression = "java(convertToFullName(teacherEntity.getFirstName(), teacherEntity.getLastName()))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    TeacherDtoForList toTeacherDtoForList(TeacherEntity teacherEntity);

    List<TeacherDtoForList> toDtoList(List<TeacherEntity> teacherEntityList);

}
