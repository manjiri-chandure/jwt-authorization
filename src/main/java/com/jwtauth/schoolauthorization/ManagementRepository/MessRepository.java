package com.jwtauth.schoolauthorization.ManagementRepository;

import com.jwtauth.schoolauthorization.ManagementEntity.MessEntity;
import com.jwtauth.schoolauthorization.config.ManagementDataSource.ManagementDatabaseConnMapper;

import java.util.List;
@ManagementDatabaseConnMapper
public interface MessRepository {
    List<MessEntity> findAllMess();
    void add(MessEntity messEntity);
    MessEntity findMessByID(Integer messId);
}
