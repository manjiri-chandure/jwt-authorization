package com.jwtauth.schoolauthorization.management.repository;

import com.jwtauth.schoolauthorization.config.management.ManagementDatabaseConnMapper;
import com.jwtauth.schoolauthorization.management.entity.MessOwnerEntity;

import java.util.List;

@ManagementDatabaseConnMapper
public interface MessOwnerRepository {
  List<MessOwnerEntity> findAllMessOwnersByMessId(Integer messId);

  List<MessOwnerEntity> findAllMessOwners();

  void add(MessOwnerEntity messOwnerEntity);
}
