package com.example.planesales.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://registry.lexa2hk.ru")
@RequestMapping("/api/v1/ping")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Ping", description = "Ping endpoint")
public class PingController {
    @Operation(
            description = "Ping endpoint",
            summary = "This is a simple ping endpoint which requires jwt",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )

    @GetMapping
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Pong");
    }
}
