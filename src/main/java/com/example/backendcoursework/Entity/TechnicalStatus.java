package com.example.backendcoursework.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TechnicalStatus")
public class TechnicalStatus {

    @Id
    private int idTechnicalStatus;

    private String status;

}