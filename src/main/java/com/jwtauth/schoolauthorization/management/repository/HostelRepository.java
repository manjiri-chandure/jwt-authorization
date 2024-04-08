package com.jwtauth.schoolauthorization.management.repository;
import com.jwtauth.schoolauthorization.management.entity.HostelEntity;
import com.jwtauth.schoolauthorization.config.ManagementDataSource.ManagementDatabaseConnMapper;

import java.util.List;
@ManagementDatabaseConnMapper
public interface HostelRepository {
    List<HostelEntity> findAllHostels();
    void add(HostelEntity hostelEntity);
}
