package com.example.backendcoursework.Controller;

import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Entity.Plane;
import com.example.backendcoursework.Service.CompanyService;
import com.example.backendcoursework.Service.FlightService;
import com.example.backendcoursework.Service.PlaneService;
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
    private final CompanyService companyService;
    private final PlaneService planeService;

    @GetMapping
    public String get() {
        companyService.fillCompanies();
        flightService.defineCompaniesToFlights();
        return "GET:: admin controller";
    }

    @PostMapping
    @Hidden
    public ResponseEntity<List<Plane>> fillFlights() {
        try {
            log.info("Admin Controller: adding flights");
            flightService.fillFlights();
            return ResponseEntity.ok(planeService.fillPlanes());
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
