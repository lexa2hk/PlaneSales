package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlane;

    private String model;
    private String calSign;
    private int capacity;
    private Date maintenance;

    @ManyToOne
    @JoinColumn(name = "TechnicalStatus_idTechnicalStatus", nullable = false)
    private TechnicalStatus technicalStatus;

    @ManyToOne
    @JoinColumn(name = "Flight_idFlight", nullable = false)
    private Flight flight;


}