package com.example.planesales.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlane;

    private String model;
    private String calSign;
    private int capacity;
    private Date maintenance;



    @ManyToOne
    @JoinColumn(name = "flight_id_flight", nullable = false)
    private Flight flight;


}