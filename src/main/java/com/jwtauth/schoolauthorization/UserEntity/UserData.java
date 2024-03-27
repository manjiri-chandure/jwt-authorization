package com.jwtauth.schoolauthorization.UserEntity;
import lombok.Data;


@Data
public class UserData{

    private Integer id;
    private String username = "";
    private String password = "";
    private String role = "";
}
