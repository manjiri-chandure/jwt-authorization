package com.jwtauth.schoolauthorization.config.SchoolDataSource;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SchoolDatabaseConnMapper{
    String value() default "";
}
