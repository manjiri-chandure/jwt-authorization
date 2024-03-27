package com.jwtauth.schoolauthorization.UserRepository;
import com.jwtauth.schoolauthorization.UserEntity.UserData;
import com.jwtauth.schoolauthorization.config.UserDataSource.UserDatabaseConnMapper;

@UserDatabaseConnMapper
public interface UserDataRepository {
    UserData findUserByUsername(String username);
}
