package com.jwtauth.schoolauthorization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class SchoolAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolAuthorizationApplication.class, args);
	}

}
