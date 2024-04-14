package com.example.planesales.Aviasales;

import com.example.planesales.Aviasales.service.IataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/iata")
@RequiredArgsConstructor
public class IataController {

    private final IataService iataService;

    @GetMapping("/airline")
    public ResponseEntity<?> getAirlineName(@RequestParam String iata) {
        String airlineName = iataService.getAirlineName(iata);
        if (airlineName != null) {
            return ResponseEntity.ok()
//                    .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                    .body("{\"name\": \"" + airlineName + "\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}