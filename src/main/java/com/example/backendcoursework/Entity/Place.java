package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlace;

    private String row_name;
    private String classType;

    @ManyToOne
//    @JoinColumn(name = "Plane_idPlane", nullable = false)
    private Plane plane;



}