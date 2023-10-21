package com.example.backendcoursework.Controller;

import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Service.FlightService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Slf4j
@Hidden
public class AdminController {

    private final FlightService flightService;

    @GetMapping
    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping
    @Hidden
    public ResponseEntity<List<Flight>> fillFlights() {
        try {
            log.info("Admin Controller: adding flights");
            return ResponseEntity.ok(flightService.fillFlights());
        } catch (Exception e) {
            log.error("Admin Controller: error while adding flights" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
