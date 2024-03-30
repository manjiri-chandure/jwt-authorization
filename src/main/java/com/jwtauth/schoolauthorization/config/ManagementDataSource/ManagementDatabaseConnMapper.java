package com.jwtauth.schoolauthorization.config.ManagementDataSource;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ManagementDatabaseConnMapper {
    String value() default "";
}
