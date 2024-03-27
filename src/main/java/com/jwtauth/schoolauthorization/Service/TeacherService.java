package com.jwtauth.schoolauthorization.Service;
import com.jwtauth.schoolauthorization.Dto.TeacherCreationDto;
import com.jwtauth.schoolauthorization.Dto.TeacherDto;
import com.jwtauth.schoolauthorization.Dto.TeacherDtoForList;
import com.jwtauth.schoolauthorization.Entity.TeacherEntity;
import com.jwtauth.schoolauthorization.Exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.MapStruct.TeacherMapper;
import com.jwtauth.schoolauthorization.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherMapper teacherMapper;

    public List<TeacherDtoForList> getTeachers(Integer minAge, Integer maxAge, String gender, String subject){
        List<TeacherEntity> teacherEntityList = this.teacherRepository.findAllTeachers(minAge, maxAge, gender, subject);
        return this.teacherMapper.toDtoList(teacherEntityList);
    }

    public TeacherDto getTeacherById(Integer id) throws ResourceNotFoundException {
        TeacherEntity teacherEntity = teacherRepository.findTeacherById(id);
        if(teacherEntity == null){
            throw new ResourceNotFoundException("teacher with id "+id+" not found");
        }
        return this.teacherMapper.toDto(teacherEntity);
    }

    public TeacherDto insertTeacher(TeacherCreationDto teacherCreationDto){
           TeacherEntity teacherEntity = this.teacherMapper.toEntity(teacherCreationDto);
           this.teacherRepository.addTeacher(teacherEntity);
           return this.teacherMapper.toDto(teacherEntity);
   }

}
