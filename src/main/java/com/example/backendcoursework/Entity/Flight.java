package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
//    @GeneratedValue
    private String idFlight;

    private String route;
    private int passengerQty;
    private Integer duration;


}