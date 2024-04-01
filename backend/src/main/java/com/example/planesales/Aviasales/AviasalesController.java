package com.example.planesales.Aviasales;

import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesResponse;
import com.example.planesales.Aviasales.service.AviasalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Date;

@RestController
public class AviasalesController {
    private final AviasalesService service;

    @Autowired
    public AviasalesController(AviasalesService service) {
        this.service = service;
    }


    @GetMapping("/tickets")
    public Flux<TicketsForSpecificDatesResponse> getTickets(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam(name="departure_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureAt,
            @RequestParam(name="return_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date returnAt,
            @RequestParam(defaultValue = "false") boolean oneWay,
            @RequestParam(defaultValue = "false") boolean direct,
            @RequestParam(defaultValue = "10") int limit) {
        return service.getTicketsForSpecificDates(origin, destination, departureAt, returnAt, oneWay, direct, limit);
    }
}
