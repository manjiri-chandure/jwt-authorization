package com.jwtauth.schoolauthorization.mapstruct;
import com.jwtauth.schoolauthorization.dto.StudentCreationDto;
import com.jwtauth.schoolauthorization.dto.StudentDto;
import com.jwtauth.schoolauthorization.dto.StudentDtoForList;
import com.jwtauth.schoolauthorization.dto.StudentDtoForSubject;
import com.jwtauth.schoolauthorization.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface StudentMapper {

    @Mapping(target="firstName", expression = "java(convertToFirstName(studentCreationDto))")
    @Mapping(target="lastName", expression = "java(convertToLastName(studentCreationDto))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(target = "id", ignore = true)
    StudentEntity toEntity(StudentCreationDto studentCreationDto);

    default String convertToFirstName(StudentCreationDto studentCreationDto){
        if (studentCreationDto.getFullName() == null){
            return null;
        }
        return studentCreationDto.getFullName().substring(0, studentCreationDto.getFullName().indexOf(" "));
    }

   default String convertToLastName(StudentCreationDto studentCreationDto){
       if (studentCreationDto.getFullName() == null){
            return null;
        }
        return studentCreationDto.getFullName().substring(studentCreationDto.getFullName().indexOf(" ") + 1);
    }

    @Mapping(source = "id", target = "id")
    @Mapping(target = "fullName", expression = "java(convertToFullName(studentEntity.getFirstName(), studentEntity.getLastName()))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "subjectEntities", target = "subjectDtoList")
    StudentDto toDto(StudentEntity studentEntity);

    default String convertToFullName(String firstName, String lastname){
        String fullName = firstName;
        fullName += " ";
        fullName += lastname;
        return  fullName;
    }
    @Mapping(source = "id", target = "id")
    @Mapping(target = "fullName", expression = "java(convertToFullName(studentEntity.getFirstName(), studentEntity.getLastName()))")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    StudentDtoForList toStudentDtoForList(StudentEntity studentEntity);
    List<StudentDtoForList> toDtoList(List<StudentEntity> studentEntityList);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "fullName", expression = "java(convertToFullName(studentEntity.getFirstName(), studentEntity.getLastName()))")
    @Mapping(source = "subjectEntities", target = "subjectDtoList")
    StudentDtoForSubject toDtoForSubject(StudentEntity studentEntity);
}
