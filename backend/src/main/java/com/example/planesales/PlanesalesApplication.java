package com.example.planesales;

import com.example.planesales.Authentication.AuthenticationService;
import com.example.planesales.Authentication.RegisterRequest;
import com.example.planesales.Roles.Role;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@OpenAPIDefinition
public class PlanesalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanesalesApplication.class, args);
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
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

        };
    }
}
