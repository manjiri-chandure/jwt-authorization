package com.jwtauth.schoolauthorization.config.UserDataSource;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UserDatabaseConnMapper {
    String value() default "";
}
