package com.example.planesales.Aviasales.db.entity;

import com.example.planesales.Aviasales.schema.TicketsForSpecificDatesData;
import com.example.planesales.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FlightsForDates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @Column(columnDefinition = "varchar(512)", unique = true)
    private String link;

    private String originAirport;

    private String destinationAirport;

    private String departureAt;

    private String airline;

    private String destination;

    private String returnAt;

    private String origin;

    private int price;

    private int returnTransfers;

    private int duration;

    private int durationTo;

    private int durationBack;

    private int transfers;

    @ManyToMany
    @JsonIgnore
    private List<User> userList;

}
