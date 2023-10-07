package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlace;

    private String row;
    private String classType;

    @ManyToOne
    @JoinColumn(name = "Plane_idPlane", nullable = false)
    private Plane plane;



}