package com.example.planesales.Controller;

import com.example.planesales.Entity.Company;
import com.example.planesales.Entity.Flight;
import com.example.planesales.Entity.Plane;
import com.example.planesales.Service.CompanyService;
import com.example.planesales.Service.FlightService;
import com.example.planesales.Service.PlaneService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority(T(com.example.planesales.Roles.Role).ADMIN)")
//https://stackoverflow.com/questions/19303584/spring-security-preauthorization-pass-enums-in-directly
@CrossOrigin(origins = "http://registry.lexa2hk.ru")
@Slf4j
@Hidden
public class AdminController {

    private final FlightService flightService;
    private final CompanyService companyService;
    private final PlaneService planeService;


    @GetMapping("/ping")
    @Hidden
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Admin pong");
    }


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

    @GetMapping("/flights")
    @Hidden
    public ResponseEntity<List<Flight>> getFlights() {
        try {
            log.info("Admin Controller: adding flights");
            return ResponseEntity.ok(flightService.getFlights());
        } catch (Exception e) {
            log.error("Admin Controller: error while getting flights" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        try {
            log.info("Admin Controller: creating flight");
            Flight createdFlight = flightService.createFlight(flight);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
        } catch (Exception e) {
            log.error("Admin Controller: error while creating flight" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update an existing flight
    @PutMapping("/flights/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightId, @RequestBody Flight flight) {
        try {
            log.info("Admin Controller: updating flight with id {}", flightId);
            Flight updatedFlight = flightService.updateFlight(flightId, flight);
            return ResponseEntity.ok(updatedFlight);
        } catch (Exception e) {
            log.error("Admin Controller: error while updating flight" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete a flight
    @DeleteMapping("/flights/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightId) {
        try {
            log.info("Admin Controller: deleting flight with id {}", flightId);
            flightService.deleteFlight(flightId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Admin Controller: error while deleting flight" + e.getMessage());
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
