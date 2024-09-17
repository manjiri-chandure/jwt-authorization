package com.jwtauth.schoolauthorization.management.repository;

import com.jwtauth.schoolauthorization.management.entity.MessOwnerEntity;
import com.jwtauth.schoolauthorization.config.ManagementDataSource.ManagementDatabaseConnMapper;

import java.util.List;

@ManagementDatabaseConnMapper
public interface MessOwnerRepository {
    List<MessOwnerEntity> findAllMessOwnersByMessId(Integer messId);
    List<MessOwnerEntity> findAllMessOwners();
    void add(MessOwnerEntity messOwnerEntity);
}
