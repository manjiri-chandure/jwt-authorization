package com.jwtauth.schoolauthorization.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


@Data
public class UserData implements GrantedAuthority {

    private Integer id;
    private String username = "";
    private String password = "";
    private String role = "";

    @Override
    public String getAuthority() {
        return this.role;
    }

}
