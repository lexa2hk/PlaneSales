package com.example.backendcoursework.Controller;


import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@Slf4j
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {

        try {
            log.info("Flight Controller: getting flights");
            return ResponseEntity.ok(flightService.getFlights());
        } catch (Exception e) {
            log.error("Flight Controller: error while getting flights" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Flight> getFlightByRoute(@PathVariable String code) {
        try {
            log.info("Flight Controller: getting flight by code");
            return ResponseEntity.ok(flightService.getFlightByRoute(code));
        } catch (Exception e) {
            log.error("Flight Controller: error while getting flight by code" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
