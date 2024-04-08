package com.jwtauth.schoolauthorization.management.mapstruct;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnerDto;
import com.jwtauth.schoolauthorization.management.entity.MessOwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessOwnerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", expression = "java(convertToFirstName(messOwnerCreationDto))")
    @Mapping(target = "lastName", expression = "java(convertToLastName(messOwnerCreationDto))")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "contactNumber", source = "contactNumber")
    @Mapping(target = "messId", source = "messId")
    MessOwnerEntity toEntity(MessOwnerCreationDto messOwnerCreationDto);
    default String convertToFirstName(MessOwnerCreationDto messOwnerCreationDto){
        if (messOwnerCreationDto.getFullName() == null){
            return null;
        }
        return messOwnerCreationDto.getFullName().substring(0, messOwnerCreationDto.getFullName().indexOf(" "));
    }
    default String convertToLastName(MessOwnerCreationDto messOwnerCreationDto){
        if (messOwnerCreationDto.getFullName() == null){
            return null;
        }
        return messOwnerCreationDto.getFullName().substring(messOwnerCreationDto.getFullName().indexOf(" ") + 1);
    }
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", expression = "java(convertToFullName(messOwnerEntity.getFirstName(), messOwnerEntity.getLastName()))")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "contactNumber", source = "contactNumber")
    @Mapping(target = "messId", source = "messId")
    MessOwnerDto toDto(MessOwnerEntity messOwnerEntity);

    default String convertToFullName(String firstName, String lastname){
        String fullName = firstName;
        fullName += " ";
        fullName += lastname;
        return  fullName;
    }
    List<MessOwnerDto> toDtoList(List<MessOwnerEntity> messOwnerEntities);
}
