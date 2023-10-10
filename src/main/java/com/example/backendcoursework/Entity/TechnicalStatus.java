package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "technical_status")
public class TechnicalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTechnicalStatus;

    private String status;

}