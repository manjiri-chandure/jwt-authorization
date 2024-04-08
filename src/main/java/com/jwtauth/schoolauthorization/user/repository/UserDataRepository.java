package com.jwtauth.schoolauthorization.user.repository;
import com.jwtauth.schoolauthorization.user.entity.UserData;
import com.jwtauth.schoolauthorization.config.UserDataSource.UserDatabaseConnMapper;

@UserDatabaseConnMapper
public interface UserDataRepository {
    UserData findUserByUsername(String username);
}
