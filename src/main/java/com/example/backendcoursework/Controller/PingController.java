package com.example.backendcoursework.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ping")
public class PingController {
    @GetMapping
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Pong");
    }
}
