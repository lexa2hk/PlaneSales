package com.example.planesales.Aviasales.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketsForSpecificDatesData {
    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("link")
    private String link;

    @JsonProperty("origin_airport")
    private String originAirport;

    @JsonProperty("destination_airport")
    private String destinationAirport;

    @JsonProperty("departure_at")
    private String departureAt;

    @JsonProperty("airline")
    private String airline;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("return_at")
    private String returnAt;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("price")
    private int price;

    @JsonProperty("return_transfers")
    private int returnTransfers;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("duration_to")
    private int durationTo;

    @JsonProperty("duration_back")
    private int durationBack;

    @JsonProperty("transfers")
    private int transfers;

    // Getters and setters
}