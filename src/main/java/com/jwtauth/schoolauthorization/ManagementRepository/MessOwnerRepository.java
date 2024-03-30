package com.jwtauth.schoolauthorization.ManagementRepository;

import com.jwtauth.schoolauthorization.ManagementEntity.MessOwnerEntity;
import com.jwtauth.schoolauthorization.config.ManagementDataSource.ManagementDatabaseConnMapper;

import java.util.List;

@ManagementDatabaseConnMapper
public interface MessOwnerRepository {
    List<MessOwnerEntity> findAllMessOwnersByMessId(Integer messId);
    List<MessOwnerEntity> findAllMessOwners();
    void add(MessOwnerEntity messOwnerEntity);
}
