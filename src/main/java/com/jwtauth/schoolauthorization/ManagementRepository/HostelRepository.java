package com.jwtauth.schoolauthorization.ManagementRepository;
import com.jwtauth.schoolauthorization.ManagementEntity.HostelEntity;
import com.jwtauth.schoolauthorization.config.ManagementDataSource.ManagementDatabaseConnMapper;

import java.util.List;
@ManagementDatabaseConnMapper
public interface HostelRepository {
    List<HostelEntity> findAllHostels();
    void add(HostelEntity hostelEntity);
}
