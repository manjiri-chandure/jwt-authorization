package com.jwtauth.schoolauthorization.service;
import com.jwtauth.schoolauthorization.dto.TeacherCreationDto;
import com.jwtauth.schoolauthorization.dto.TeacherDto;
import com.jwtauth.schoolauthorization.dto.TeacherDtoForList;
import com.jwtauth.schoolauthorization.entity.TeacherEntity;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.exception.UnauthorizedException;
import com.jwtauth.schoolauthorization.mapstruct.TeacherMapper;
import com.jwtauth.schoolauthorization.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherMapper teacherMapper;


        public List<TeacherDtoForList> getTeachers (Integer minAge, Integer maxAge, String gender, String subject){
            List<TeacherEntity> teacherEntityList = this.teacherRepository.findAllTeachers(minAge, maxAge, gender, subject);
            return this.teacherMapper.toDtoList(teacherEntityList);
        }


        public TeacherDto getTeacherById (Integer id){

             TeacherEntity teacherEntity = teacherRepository.findTeacherById(id);

            if (teacherEntity == null) {
                throw new ResourceNotFoundException("teacher with id " + id + " not found");
            }
            return this.teacherMapper.toDto(teacherEntity);
        }


        public TeacherDto insertTeacher (TeacherCreationDto teacherCreationDto){
            TeacherEntity teacherEntity = this.teacherMapper.toEntity(teacherCreationDto);
            this.teacherRepository.addTeacher(teacherEntity);
            return this.teacherMapper.toDto(teacherEntity);
        }

    }

