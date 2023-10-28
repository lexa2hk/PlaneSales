package com.example.backendcoursework.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "technical_status")
public class TechnicalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTechnicalStatus;

    private String status;

}