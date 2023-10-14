package com.example.backendcoursework;

import com.example.backendcoursework.Authentication.AuthenticationService;
import com.example.backendcoursework.Authentication.RegisterRequest;
import com.example.backendcoursework.Entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendCourseworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCourseworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());

        };
    }
}
