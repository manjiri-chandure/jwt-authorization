package com.jwtauth.schoolauthorization.management.repository;

import com.jwtauth.schoolauthorization.management.entity.MessEntity;
import com.jwtauth.schoolauthorization.config.management.datasource.ManagementDatabaseConnMapper;

import java.util.List;

@ManagementDatabaseConnMapper
public interface MessRepository {
  List<MessEntity> findAllMess();

  void add(MessEntity messEntity);

  MessEntity findMessByID(Integer messId);
}
