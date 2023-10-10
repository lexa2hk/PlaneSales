package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
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
    @JoinColumn(name = "technical_status_id_technical_status", nullable = false)
    private TechnicalStatus technicalStatus;

    @ManyToOne
    @JoinColumn(name = "flight_id_flight", nullable = false)
    private Flight flight;


}