package com.example.planesales.Aviasales.service;

import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesParams;
import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class AviasalesService {
    public final WebClient webClient;

    @Autowired
    public AviasalesService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<TicketsForSpecificDatesResponse> getTicketsForSpecificDates(String origin, String destination,
                                                                            Date departureAt, Date returnAt,
                                                                            boolean oneWay, boolean direct,
                                                                            int limit) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        TicketsForSpecificDatesParams params = TicketsForSpecificDatesParams.builder()
                .origin(origin)
                .destination(destination)
                .departureAt(formatter.format(departureAt))
                .returnAt(formatter.format(returnAt))
                .oneWay(oneWay)
                .direct(direct)
                .limit(limit)
                .build();


        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v3/prices_for_dates")
                        .queryParams(params.toQueryParams())
                        .build()
                )
                .retrieve()
                .bodyToFlux(TicketsForSpecificDatesResponse.class)
                .doOnError(throwable -> System.out.println(throwable.getMessage()));
    }
}
