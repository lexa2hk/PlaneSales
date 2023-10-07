package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Flight")
public class Flight {

    @Id
    private String idFlight;

    private String route;
    private int passengerQty;
    private Integer duration;

    // Constructors, getters, and setters (using Lombok @Data)
    // You don't need to write these explicitly due to @Data annotation

}