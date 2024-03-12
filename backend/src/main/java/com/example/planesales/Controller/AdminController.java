package com.example.planesales.Controller;

import com.example.planesales.Entity.Company;
import com.example.planesales.Entity.Plane;
import com.example.planesales.Service.CompanyService;
import com.example.planesales.Service.FlightService;
import com.example.planesales.Service.PlaneService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@Hidden
public class AdminController {

    private final FlightService flightService;
    private final CompanyService companyService;
    private final PlaneService planeService;


    @PostMapping("/flights/fill")
    @Hidden
    public ResponseEntity<List<Plane>> fillFlights() {
        try {
            log.info("Admin Controller: adding flights");
            flightService.fillFlightsWithCompanies();
            return ResponseEntity.ok(planeService.fillPlanes());
        } catch (Exception e) {
            log.error("Admin Controller: error while adding flights" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/companies/fill")
    @Hidden
    public ResponseEntity<List<Company>> fillCompanies() {
        try {
            log.info("Admin Controller: adding flights");
            companyService.fillCompanies();
            return ResponseEntity.ok(companyService.fillCompanies());
        } catch (Exception e) {
            log.error("Admin Controller: error while adding flights" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
