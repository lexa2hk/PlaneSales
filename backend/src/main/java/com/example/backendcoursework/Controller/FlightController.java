package com.example.backendcoursework.Controller;


import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Flights", description = "Flights API")
public class FlightController {

    private final FlightService flightService;


    @Operation(
            description = "Get a list of flights",
            summary = "Get a list of all available flights",
            responses = {
                    @ApiResponse(description = "Successful retrieval of flights", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class)))),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(description = "Not authorized", responseCode = "403")
            }
    )
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


    @Operation(
            description = "Get a flight by code",
            summary = "Get a flight by its route code",
            responses = {
                    @ApiResponse(description = "Successful retrieval of the flight", responseCode = "200", content = @Content(schema = @Schema(implementation = Flight.class))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500"),
                    @ApiResponse(description = "Not authorized", responseCode = "403")
            }

    )
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
